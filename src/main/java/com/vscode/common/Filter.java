package com.vscode.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Utkarsh Bhardwaj
 */

public class Filter implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2504359937793068240L;

	private String orderBy = null;
	private SortType sort = SortType.ASC;
	private Aggregate aggFunc = null;
	private String aggColumn = null;
	private String queryString = null;
	private String additionString = null;
	private boolean isOrderByCharToNumber = false;
	private boolean isDistinct = false;
	private List<Criteria> criteriaSet = null;
	private List<String> selectColumns = null;
	private List<String> groupByColumns = null;

	public Filter() {
		criteriaSet = new ArrayList<Criteria>();
	}

	public void addCriteria(Criteria e) {
		criteriaSet.add(e);
	}

	public void addCriteria(String column, CriteriaType criteriaType, String value) {
		Criteria criteria = new Criteria(column, criteriaType, value);
		criteriaSet.add(criteria);
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public SortType getSort() {
		return sort;
	}

	public void setSort(SortType sort) {
		this.sort = sort;
	}

	public Aggregate getAggFunc() {
		return aggFunc;
	}

	public String getAggColumn() {
		return aggColumn;
	}

	public void setAggregate(Aggregate aggFunc, String col) {
		this.aggFunc = aggFunc;
		this.aggColumn = col;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public String getAdditionString() {
		return additionString;
	}

	public void setAdditionString(String additionString) {
		this.additionString = additionString;
	}

	public boolean isOrderByCharToNumber() {
		return isOrderByCharToNumber;
	}

	public void setOrderByCharToNumber(boolean isOrderByCharToNumber) {
		this.isOrderByCharToNumber = isOrderByCharToNumber;
	}

	public boolean isDistinct() {
		return isDistinct;
	}

	public void setDistinct(boolean isDistinct) {
		this.isDistinct = isDistinct;
	}

	public List<Criteria> getCriteriaSet() {
		return criteriaSet;
	}

	public void setCriteriaSet(List<Criteria> criteriaSet) {
		if (criteriaSet != null && !criteriaSet.isEmpty())
			this.criteriaSet = criteriaSet;
	}

	public List<String> getSelectColumns() {
		return selectColumns;
	}

	public void setSelectColumns(List<String> selectColumns) {
		if (selectColumns != null && !selectColumns.isEmpty())
			this.selectColumns = selectColumns;
	}

	public void addSelectColumn(String selectColumn) {
		if (selectColumns == null)
			selectColumns = new ArrayList<>();
		selectColumns.add(selectColumn);
	}

	public void addGroupByColumn(String column) {
		if (this.groupByColumns == null)
			groupByColumns = new ArrayList<>();

		groupByColumns.add(column);
	}

	public List<String> getGroupByColumns() {
		return groupByColumns;
	}

	public void setGroupByColumns(List<String> groupByColumns) {
		if (groupByColumns != null && !groupByColumns.isEmpty())
			this.groupByColumns = groupByColumns;
	}

}
