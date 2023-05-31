package com.vscode.common.repo;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;

import org.hibernate.Session;
import org.hibernate.engine.jdbc.spi.JdbcServices;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Table;
import org.hibernate.metamodel.internal.MetamodelImpl;
import org.hibernate.persister.entity.EntityPersister;

import com.vscode.common.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.vscode.common.Aggregate;
import com.vscode.common.CommonUtil;
import com.vscode.common.Criteria;
import com.vscode.common.CriteriaType;

/**
 * @author Utkarsh Bhardwaj
 */
public class DefaultRepoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements DefaultRepo<T, ID> {
	@Autowired
	protected EntityManager entityManager;

	protected JpaEntityInformation<T, ?> entityInformation;

	private static final String PR_NEW_FINGERPRINT_ID = "ATTENDANCE.NEW_FINGERPRINT_ID";

	public DefaultRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
	}

	@Override
	/**
	 * specifically created for executing custom Select Query so that we can have
	 * dynamic where clause
	 **/
	public List<Map<String, Object>> loadAll(Filter filter) {
		try {
			// springboot jpa hibernate
			Session session = entityManager.unwrap(Session.class);

			// JDBC Services
			JdbcServices jdbcServices = ((SessionFactoryImplementor) session.getSessionFactory()).getJdbcServices();

			// getting current Catalog in use or Database in use for MySQL
			String currentCatalog = jdbcServices.getJdbcEnvironment().getCurrentCatalog().toString();

			if (currentCatalog == null)
				return new ArrayList<Map<String, Object>>();

			/**
			 * Using object of our implemented Integrator(CustomIntegrator) to get the
			 * PersistentClass for the specified entity currently used in query to get the
			 * object of table
			 **/
			Table table = CustomIntegrator.getMap(currentCatalog).getMetadata()
					.getEntityBinding(getDomainClass().getName()).getTable();

			// Using hibernate and jdbc
			return createNativeQuery(table, jdbcServices, filter);

			// hibernate
//		    System.out.println(customManager.getDynaBean());
//			Query<Map<String, Object>> result = session.createQuery(createSpecialSelectQuery(filter))
//					.setResultTransformer(new ResultTransformer() {
//
//						@Override
//						public Object transformTuple(Object[] tuple, String[] aliases) {
//							Map<String, Object> data = new HashMap<String, Object>();
//							for (int i = 0; i < tuple.length; i++) {
//								System.out.print(tuple[i]);
//								data.put(String.valueOf(i), tuple[i]);
//							}
//							
//							return data;
//						}
//
//						@Override
//						public List transformList(List collection) {
//							return (List<Map<String, Object>>) collection;
//						}
//					});
////			
//			return (List<Map<String, Object>>) session.createQuery(createSpecialSelectQuery(filter)).list();
//			return resultList;
//		return (ArrayList<Map<String, Object>>)query.getResultList();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ArrayList<Map<String, Object>>();
	}

	private String createSpecialSelectQuery(String tableName, com.vscode.common.Filter filter) {
		try {
			if(filter.getQueryString()!=null && !filter.getQueryString().trim().isEmpty())
				return filter.getQueryString();
			
			StringBuilder selectQuery = new StringBuilder("Select ");

			List<String> selectCols = filter.getSelectColumns();

			Aggregate aggFunc = filter.getAggFunc();
			boolean aggUsed = !CommonUtil.checkNull(aggFunc);
			boolean isDistinct = filter.isDistinct();

			if (aggUsed) {
				if (isDistinct) {
					selectQuery.append("Distinct ");
					isDistinct = false;
				}
				selectQuery.append(aggFunc.toString()).append("(").append(filter.getAggColumn()).append(")");
			}

			if (!CommonUtil.checkNull(selectCols)) {

				if (aggUsed)
					selectQuery.append(", ");

				if (isDistinct)
					selectQuery.append("Distinct ");

				selectCols.forEach(in -> {
					selectQuery.append(in);
					if (selectCols.indexOf(in) < selectCols.size() - 1)
						selectQuery.append(", ");
					else
						selectQuery.append(" ");

				});
			} else
				selectQuery.append("* ");

			selectQuery.append("from ").append(tableName);
			if (filter.getCriteriaSet() != null && !filter.getCriteriaSet().isEmpty())
				selectQuery.append(" where " + filterQuery(filter));
			if (!CommonUtil.checkNull(filter.getOrderBy())) {
				selectQuery.append(" order by ").append(filter.getOrderBy()).append(" " + filter.getSort().toString());
			}

			if (!CommonUtil.checkNull(filter.getGroupByColumns()))
				filter.getGroupByColumns().stream()
						.forEach(in -> selectQuery.append(" group by ").append(filter.getGroupByColumns()));

			System.out.println(selectQuery.toString() + ";");
			return selectQuery.toString();
		} catch (Exception e) {
			System.out.println(e);
			return new String("");
		}

	}

	protected String filterQuery(Filter filter) {
		try {
			StringBuilder whereClause = new StringBuilder();

			if (!CommonUtil.checkNull(filter.getQueryString()))
				whereClause.append(filter.getQueryString());

			List<Criteria> criteria = filter.getCriteriaSet();

			if (!CommonUtil.checkNull(filter.getAdditionString())) {
				whereClause.append(filter.getAdditionString());
				if (!CommonUtil.checkNullCriteria(criteria))
					whereClause.append(" AND");
			}
			if (!CommonUtil.checkNullCriteria(criteria)) {
				criteria.forEach(criterion -> {
					if (!criterion.equals(criteria.get(0)) && criteria.size() > 1)
						whereClause.append(" AND ");

					whereClause.append(criterion.getColumn());
					CriteriaType criteriaType = criterion.getCriteriaType();
					boolean hasValue = true;
					switch (criteriaType) {
					case Equals:
						whereClause.append(" =");
						break;

					case NotEqual:
						whereClause.append(" <>");

					case GreaterThan:
						whereClause.append(" >");
						break;

					case GreaterEqual:
						whereClause.append(" >=");
						break;

					case LessThan:
						whereClause.append(" <");
						break;

					case LessEqual:
						whereClause.append(" <=");
						break;

					case Like:
						whereClause.append(" like '%").append(criterion.getValue()).append("%'");
						hasValue = false;
						break;

					case LikeStartsWith:
						whereClause.append(" like '").append(criterion.getValue()).append("%'");
						hasValue = false;
						break;

					case LikeEndsWith:
						whereClause.append(" like '%").append(criterion.getValue()).append("'");
						hasValue = false;
						break;

					case Or:
						whereClause.append(" or ");
						hasValue = false;
						break;

					case And:
						whereClause.append(" and ");
						hasValue = false;
						break;
					case IsNull:
						whereClause.append(" isnull ");
						hasValue = false;
						break;

					case NotNull:
						whereClause.append(" is not null");
						hasValue = false;
						break;
					case In:
						whereClause.append(" IN ( ");
						List<String> inValues = criterion.getInValues();
						inValues.forEach(in -> {
							whereClause.append(in);
							if (inValues.get(inValues.size() - 1) != in)
								whereClause.append(", ");
						});
						whereClause.append(")");
						hasValue = false;
						break;
					case LeftParenthesis:
						whereClause.append("(");
						hasValue = false;
						break;
					case Rightparenthesis:
						whereClause.append(")");
						hasValue = false;
						break;
					}

					if (criterion.getValue() != null && hasValue)
						appendValue(whereClause, true, 0, criterion.getValue());

				});

				return whereClause.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

//	public void createUpdateStatement(com.vscode.common.Filter filter) {
//		StringBuilder update = new StringBuilder("Update");
//		update.append(getDomainClass().getName());
//		update.append(" set ");
//
//	}

	private void appendValue(StringBuilder clause, boolean isString, int dataType, String value) {
		if (isString)
			clause.append("'").append(value).append("'");
		else
			clause.append(value);
	}

	@SuppressWarnings("restriction")
	private List<Map<String, Object>> getListOfMap(Iterator<Column> columns, ResultSet resultSet, Filter filter)
			throws SQLException, NativeException {
		List<Map<String, Object>> dynaBeans = new ArrayList<Map<String, Object>>();

		if (columns == null)
			throw new NativeException("Columns are not listed means issue in connectivity our "
					+ "database check in CustomIntegrator or DefaultRepoImpl");

		List<String> columnNames = getColumnList(columns, filter);

		while (resultSet.next()) {

			Map<String, Object> dynaBean = new HashMap<>();

			for(String column:columnNames) {

				try {

					if (column != null && resultSet.getObject(column) != null) {
//						System.out.println(resultSet.getMetaData().getColumnType(resultSet.findColumn(column)));
						if (resultSet.getMetaData()
								.getColumnType(resultSet.findColumn(column)) == Types.LONGVARBINARY) {
							Blob blob = resultSet.getBlob(column);
//							File file = new File("D:/image.jpeg");
//
//							BufferedImage buff = ImageIO.read(
//									new ByteArrayInputStream(blob.getBytes(1, (int) blob.length())));
//							ImageIO.write(buff, "jpeg", file);
							dynaBean.put(column, Base64.getEncoder()
									.encode(blob.getBinaryStream(1, blob.length()).readAllBytes()).toString());
						} else
							dynaBean.put(column, resultSet.getObject(column).toString());

					}}
				catch (SQLException | IOException e) {
					e.printStackTrace();
				}
			}

			dynaBeans.add(dynaBean);

		}
		dynaBeans.forEach(in -> in.forEach((key, val) -> System.out.println(key + " " + val)));

		return dynaBeans;
	}

	private List<String> getColumnList(Iterator<Column> iterator, Filter filter) {
		List<String> list = new ArrayList<>();
		boolean selectedCols = !CommonUtil.checkNull(filter.getSelectColumns());
		boolean aggUsed = filter.getAggFunc() != null;

		if (selectedCols)
			filter.getSelectColumns().forEach(in -> list.add(in));

		if (aggUsed)
			list.add(new StringBuilder().append(filter.getAggFunc().toString()).append("(")
					.append(filter.getAggColumn()).append(")").toString());

		else if (!selectedCols && !aggUsed)
			iterator.forEachRemaining(in -> list.add(in.getName()));

		return list;
	}

	/**
	 * getDynaBeans can be used while we use any predefined method for doing any
	 * Read Operation in Service Class
	 * 
	 * @param List<Entity>
	 * @return DynaBeans(List<Map<String, Object>>)
	 **/
	public List<Map<String, Object>> getDynaBeans(List<T> entities) {
		List<Map<String, Object>> dynaBeans = new ArrayList<>();

		EntityPersister entityPersister = ((MetamodelImpl) entityManager.getMetamodel())
				.entityPersister(getDomainClass().getName());

		// not more than 128 columns will be allowed
		entities.forEach(entity -> {
			Map<String, Object> data = new HashMap<String, Object>();
			String[] names = entityPersister.getPropertyNames();
			for (byte i = 0; i < names.length; i++)
				data.put(names[i], entityPersister.getPropertyValue(entity, i));
			dynaBeans.add(data);
		});

		return dynaBeans;
	}

//	public void update(String column, Object value, Expression<Boolean> criteria) {
//		if(column != null && value != null && criteria != null)
//         entityManager.getCriteriaBuilder().createCriteriaUpdate(getDomainClass()).set(column, value).where(criteria);
//	}

	private List<Map<String, Object>> createNativeQuery(Table table, JdbcServices jdbcServices, Filter filter)
			throws SQLException {

		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;

		try {
			// using JDBCServices to obtain the object of Connection
			conn = jdbcServices.getBootstrapJdbcConnectionAccess().obtainConnection();
			statement = conn.prepareStatement(createSpecialSelectQuery(table.getName(), filter));
			rs = statement.executeQuery();

			if (rs == null)
				return null;

			return getListOfMap(table.getColumnIterator(), rs, filter);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NativeException e) {
			System.out.println(e);
		} finally {
			closeResources(conn, statement, rs);
		}
		throw new SQLException();
//		SqlExceptionHelper spi
	}

	// don't remove @Transactional annotation as it is required for calling a
	// procedure it's not creating any read only statement,
	// whether u have made changes in the database or not creating the
	// procedure but calling a procedure is considered itself
	// as making changes in db.
	@Override
	@Transactional
	public Map<String, Object> callProcedure() {
		Map<String, Object> map = new HashMap<String, Object>();
		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(PR_NEW_FINGERPRINT_ID);

		query.registerStoredProcedureParameter("NEW_FINGERPRINT_ID", Byte.class, ParameterMode.OUT);
		query.registerStoredProcedureParameter("NEW_EMPLOYEE_ID", Short.class, ParameterMode.OUT);

		if (!query.execute()) {
			map.put("FINGERPRINT_ID", query.getOutputParameterValue("NEW_FINGERPRINT_ID"));
			map.put("EMPLOYEE_ID", query.getOutputParameterValue("NEW_EMPLOYEE_ID"));
		}
		return map;
	}

	private void closeResources(Connection conn, PreparedStatement statement, ResultSet rs) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
		}
		try {
			if (rs != null)
				rs.close();
		} catch (SQLException e) {

		}
		try {
			if (statement != null)
				statement.close();
		} catch (SQLException e) {

		}

	}

}
