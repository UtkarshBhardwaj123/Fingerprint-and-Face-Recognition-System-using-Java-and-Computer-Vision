package com.vscode.entity.attendance;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Utkarsh Bhardwaj 
 */

@Entity
@Table(name="STUDENT_DETAILS", schema="ATTENDANCE")
public class StudentDetailsBean extends StudentDetails {

	public StudentDetailsBean() {
		super();
	}

	public StudentDetailsBean(String name, String fathersName, String mothersName, BigInteger admissionNo,
			Date admissionDate, byte rollNo, String batch, String section, String stdStatus) {
		super(name, fathersName, mothersName, admissionNo, admissionDate, rollNo, batch, section, stdStatus);
	}

}
