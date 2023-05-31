package com.vscode.entity.attendance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_DETAILS")
public class EmployeeDetailsBean {
	@Id
	@Column(name = "EMPLOYEE_ID")
	short employeeId;

	@Column(name = "EMPLOYEE_NAME")
	String employeeName;

	@Column(name = "PROBATION_PERIOD")
	short probationPeriod;

	@Column(name = "CONFIRMATION_DATE")
	Date confirmDate;

	@Column(name = "DOB")
	Date dob;

	@Column(name = "MOBILE_NO")
	long mobileNo;

	@Column(name = "EMERGENCY_CONTACT_NAME")
	String emergencyName;

	@Column(name = "EMERGENCY_CONTACT_NO")
	long emergencyCntNo;

	@Column(name = "WORKING_STATUS")
	String workingStatus;

	@Column(name = "FATHERS_NAME")
	String fathersName;

	@Column(name = "SPOUSE_NAME")
	String spouseName;

	@Column(name = "DATE_OF_JOINING")
	Date doj;

	@Column(name = "GENDER")
	String gender;

	@Column(name = "DEPARTMENT")
	String department;

	@Column(name = "DIVISON")
	String division;

	@Column(name = "GRADE")
	String grade;

	@Column(name = "LOCATION")
	String location;

	public EmployeeDetailsBean() {
		// TODO Auto-generated constructor stub
	}

	public EmployeeDetailsBean(byte employeeId, String employeeName, short probationPeriod, Date confirmDate, Date dob,
			long mobileNo, String emergencyName, long emergencyCntNo, String workingStatus, String fathersName,
			String spouseName, Date doj, String gender, String department, String division, String grade,
			String location) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.probationPeriod = probationPeriod;
		this.confirmDate = confirmDate;
		this.dob = dob;
		this.mobileNo = mobileNo;
		this.emergencyName = emergencyName;
		this.emergencyCntNo = emergencyCntNo;
		this.workingStatus = workingStatus;
		this.fathersName = fathersName;
		this.spouseName = spouseName;
		this.doj = doj;
		this.gender = gender;
		this.department = department;
		this.division = division;
		this.grade = grade;
		this.location = location;
	}

	public short getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(short employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public short getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(short probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public Date getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Date confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public long getEmergencyCntNo() {
		return emergencyCntNo;
	}

	public void setEmergencyCntNo(long emergencyCntNo) {
		this.emergencyCntNo = emergencyCntNo;
	}

	public String getWorkingStatus() {
		return workingStatus;
	}

	public void setWorkingStatus(String workingStatus) {
		this.workingStatus = workingStatus;
	}

	public String getFathersName() {
		return fathersName;
	}

	public void setFathersName(String fathersName) {
		this.fathersName = fathersName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public Date getDoj() {
		return doj;
	}

	public void setDoj(Date doj) {
		this.doj = doj;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
