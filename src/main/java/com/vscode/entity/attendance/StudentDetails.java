package com.vscode.entity.attendance;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;


/**
 * @author Utkarsh Bhardwaj 
 */

@MappedSuperclass
public class StudentDetails {
	private String name;
	private String fathersName;
	private String mothersName;

	// Primary Key
	@Id
	private BigInteger admissionNo;

	private Date admissionDate;
	private byte rollNo;
	private String batch;
	private String section;
	private String stdStatus;

	public StudentDetails() {
		super();
	}

	public StudentDetails(String name, String fathersName, String mothersName, BigInteger admissionNo,
			Date admissionDate, byte rollNo, String batch, String section, String stdStatus) {
		super();
		this.name = name;
		this.fathersName = fathersName;
		this.mothersName = mothersName;
		this.admissionNo = admissionNo;
		this.admissionDate = admissionDate;
		this.rollNo = rollNo;
		this.batch = batch;
		this.section = section;
		this.stdStatus = stdStatus;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setMothersName(String mothersName) {
		this.mothersName = mothersName;
	}

	public String getMothersName() {
		return mothersName;
	}

	public void setAdmissionNo(BigInteger admissionNo) {
		this.admissionNo = admissionNo;
	}

	public BigInteger getAdmisionNo() {
		return admissionNo;
	}

	public byte getRollNo() {
		return rollNo;
	}

	public void setRollNo(byte rollNo) {
		this.rollNo = rollNo;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public Date getAdmissionDate() {
		return admissionDate;
	}

	public void setAdmissionDate(Date admissionDate) {
		this.admissionDate = admissionDate;
	}

	public String getStdStatus() {
		return stdStatus;
	}

	public void setStdStatus(String stdStatus) {
		this.stdStatus = stdStatus;
	}

}
