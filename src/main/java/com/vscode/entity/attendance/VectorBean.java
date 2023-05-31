package com.vscode.entity.attendance;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="VECTOR", schema="ATTENDANCE")
@IdClass(VectorPKId.class)
public class VectorBean {
	
	@Id
	@Column(name="ARRAY_ID")
	private String id;
	
	@Id
	@Column(name="INDEX")
	private int index;
	
	@Column(name="VALUE")
	private double value;
	
	public VectorBean() {
		// TODO Auto-generated constructor stub
	}
	public VectorBean(String id, int index, double value) {
		super();
		this.id = id;
		this.index = index;
		this.value = value;
	}
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
}
