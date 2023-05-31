package com.vscode.entity.attendance;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * @author Utkarsh Bhardwaj
 */

@Entity
@Table(name = "STUDENT_ATTENDANCE")
@IdClass(StudentAttendancePKId.class)
public class StudentAttendanceBean {

	@Column(name = "NAME")
	private String name;

	// primary keys
	@Id
	@Column(name = "ROLL_NO")
	private byte rollNo;
	@Id
	@Column(name = "CLASS")
	private String batch;
	@Id
	@Column(name = "SECTION")
	private String section;

	@Column(name = "TOTAL_DAYS_OPENED")
	private int totalDaysOpened;

	@Column(name = "TOTAL_DAYS_PRESENT")
	private int totalDaysPresent;

	@Column(name = "TOTAL_DAYS_ABSENT")
	private int totalDaysAbsent;

	@Column(name = "LAST_ATTENDANCE_DAY")
	private Date lastAttendanceDay;

	@Column(name = "STUDENT_STATUS")
	private String studentStatus;

	public StudentAttendanceBean() {
		super();
	}

	public StudentAttendanceBean(String name, byte rollNo, String batch, String section, int totalDaysOpened,
			int totalDaysPresent, int totalDaysAbsent, Date lastAttendanceDay, String studentStatus) {
		super();
		this.name = name;
		this.rollNo = rollNo;
		this.batch = batch;
		this.section = section;
		this.totalDaysOpened = totalDaysOpened;
		this.totalDaysPresent = totalDaysPresent;
		this.totalDaysAbsent = totalDaysAbsent;
		this.lastAttendanceDay = lastAttendanceDay;
		this.studentStatus = studentStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int gettotalDaysOpened() {
		return totalDaysOpened;
	}

	public void settotalDaysOpened(int totalDaysOpened) {
		this.totalDaysOpened = totalDaysOpened;
	}

	public int getTotalDaysPresent() {
		return totalDaysPresent;
	}

	public void setTotalDaysPresent(int totalDaysPresent) {
		this.totalDaysPresent = totalDaysPresent;
	}

	public int getTotalDaysAbsent() {
		return totalDaysAbsent;
	}

	public void setTotalDaysAbsent(int totalDaysAbsent) {
		this.totalDaysAbsent = totalDaysAbsent;
	}

	public Date getLastAttendanceDay() {
		return lastAttendanceDay;
	}

	public void setLastAttendanceDay(Date lastAttendanceDay) {
		this.lastAttendanceDay = lastAttendanceDay;
	}

	public String getStudentStatus() {
		return studentStatus;
	}

	public void setStudentStatus(String studentStatus) {
		this.studentStatus = studentStatus;
	}
}
