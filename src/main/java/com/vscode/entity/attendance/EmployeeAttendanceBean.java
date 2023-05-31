package com.vscode.entity.attendance;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_ATTENDANCE")
@IdClass(EmployeeAttendancePKID.class)

public class EmployeeAttendanceBean {
//primary key
	@Id
	@Column(name = "FINGERPRINT_ID")
	byte fingerPrintId;

	@Id
	@Column(name = "EMPLOYEE_ID")
	short employeeId;
//
	
	@Column(name = "WORKING_DAYS")
	short workingDays;

	@Column(name = "DAYS_PRESENT")
	short daysPresent;

	@Column(name = "DAYS_ABSENT")
	short daysAbsent;

	@Column(name = "DAYS_LEFT")
	short daysLeft;

	@Column(name = "ATTENDANCE_STATUS")
	String attendanceStatus;

	@Column(name = "LAST_ATTENDANCE_DAY")
	Date lastAttendanceDay;

	@Column(name = "EMPLOYEE_NAME")
	String employeeName;
	
	@Column(name = "EMPLOYEE_IMAGE")
	@Lob
	private Blob employeeImage;
	
	@Column(name ="ARRAY_ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Blob getEmployeeImage() {
		return employeeImage;
	}

	public void setEmployeeImage(Blob employeeImage) {
		this.employeeImage = employeeImage;
	}

	public EmployeeAttendanceBean() {

	}

	public byte getFingerPrintId() {
		return fingerPrintId;
	}

	public void setFingerPrintId(byte fingerPrintId) {
		this.fingerPrintId = fingerPrintId;
	}

	public short getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(short employeeId) {
		this.employeeId = employeeId;
	}

	public short getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(short workingDays) {
		this.workingDays = workingDays;
	}

	public short getDaysPresent() {
		return daysPresent;
	}

	public void setDaysPresent(short daysPresent) {
		this.daysPresent = daysPresent;
	}

	public short getDaysAbsent() {
		return daysAbsent;
	}

	public void setDaysAbsent(short daysAbsent) {
		this.daysAbsent = daysAbsent;
	}

	public short getDaysLeft() {
		return daysLeft;
	}

	public void setDaysLeft(short daysLeft) {
		this.daysLeft = daysLeft;
	}

	public String getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(String attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public Date getLastAttendanceDay() {
		return lastAttendanceDay;
	}

	public void setLastAttendanceDay(Date lastAttendanceDay) {
		this.lastAttendanceDay = lastAttendanceDay;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

}
