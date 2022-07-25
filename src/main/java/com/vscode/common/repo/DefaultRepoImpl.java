package com.vscode.common.repo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.hibernate.transform.ResultTransformer;
import org.hibernate.transform.ToListResultTransformer;
import org.hibernate.transform.Transformers;
//import com.vscode.common.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.vscode.common.CommonUtil;
import com.vscode.common.Criteria;
import com.vscode.common.CriteriaType;

/**
 * @author Utkarsh Bhardwaj
 */
public class DefaultRepoImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
		implements DefaultRepo<T, ID> {
	@Autowired
	private EntityManager entityManager;

	private JpaEntityInformation<T, ?> entityInformation;

	public DefaultRepoImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.entityManager = entityManager;
		this.entityInformation = entityInformation;
	}

//	public DefaultRepoImpl(Class<T> domainClass, EntityManager entityManager) {
//		super(domainClass, entityManager);
//		this.entityManager = entityManager;
//	}
//j
	@Override
	public List<Map<String, Object>> loadAll(com.vscode.common.Filter filter) {
		try {
			Session session = entityManager.unwrap(Session.class);

		List<LinkedHashMap<String,Object>> result = (List<LinkedHashMap<String, Object>>) session.createQuery(createSpecialSelectQuery(filter)).list();
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
//			
			return (List<Map<String, Object>>) session.createQuery(createSpecialSelectQuery(filter)).list();
//			return resultList;
//		return (ArrayList<Map<String, Object>>)query.getResultList();

		} catch (HibernateException e) {
			e.printStackTrace();

		}
		return new ArrayList<Map<String, Object>>();
	}

	@Override
	public String createSpecialSelectQuery(com.vscode.common.Filter filter) {
		try {
			StringBuilder selectQuery = new StringBuilder("Select new map( ");

			List<String> selectCols = filter.getSelectColumns();

			if (!CommonUtil.checkNull(selectCols)) {

				if (filter.isDistinct() && !CommonUtil.checkNull(selectCols))
					selectQuery.append("Distinct ");

				selectCols.stream().forEach(in -> {
					selectQuery.append(in);
					if (selectCols.indexOf(in) < selectCols.size() - 1)
						selectQuery.append(", ");

				});
			}
			System.out.println(getDomainClass());
			selectQuery.append(" ) from ").append(entityInformation.getEntityName() + " e");

			if (filter != null) {
				selectQuery.append(" where " + filterQuery(filter));
				if (!CommonUtil.checkNull(filter.getOrderBy())) {
					selectQuery.append(" order by ").append(filter.getOrderBy()).append(filter.getSort().toString());
				}

				if (!CommonUtil.checkNull(filter.getGroupByColumns()))
					filter.getGroupByColumns().stream()
							.forEach(in -> selectQuery.append(" group by ").append(filter.getGroupByColumns()));
			}

			System.out.println(selectQuery.toString());
			return selectQuery.toString();
		} catch (Exception e) {
			System.out.println(e);
			return new String("");
		}

	}

	@Override
	public String filterQuery(com.vscode.common.Filter filter) {
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
				for (Criteria criterion : criteria) {
					System.out.println("yes");
					if (!criterion.equals(criteria.get(0)) && criteria.size() > 1)
						whereClause.append(" AND ");

					whereClause.append("e." + criterion.getColumn());
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
						whereClause.append(" IN ").append(
								criterion.getInValues().stream().map(in -> in + ",").collect(Collectors.toList()));
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

				}
				return whereClause.toString();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public void createUpdateStatement(com.vscode.common.Filter filter) {
		StringBuilder update = new StringBuilder("Update");
		update.append(getDomainClass().getName());

	}

	private void appendValue(StringBuilder clause, boolean isString, int dataType, String value) {
		if (isString)
			clause.append("'").append(value).append("'");
		else
			clause.append(value);
	}

}
