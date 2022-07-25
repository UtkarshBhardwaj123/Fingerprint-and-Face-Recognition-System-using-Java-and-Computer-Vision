package com.vscode.common;

import java.io.Serializable;
import java.util.List;
/**
 * @author Utkarsh Bhardwaj 
 */

public class Criteria implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4695191746262654512L;

	private String column = null;
	private CriteriaType criteriaType;
	private String value;
	
	// only for in clause
	private List<String> inValues;

	public Criteria(String column, CriteriaType criteriaType, String value) {
		this.column = column;
		this.criteriaType = criteriaType;
		this.value = value;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public CriteriaType getCriteriaType() {
		return criteriaType;
	}

	public void setCriteriaType(CriteriaType criteriaType) {
		this.criteriaType = criteriaType;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<String> getInValues() {
		return inValues;
	}

	public void setInValues(List<String> inValues) {
		this.inValues = inValues;
	}

}
