package com.vscode.entity.attendance;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Utkarsh Bhardwaj 
 */

public class StudentAttendancePKId implements Serializable {

	/** serialVersionUID
	 * 
	 */
	private static final long serialVersionUID = -5317252665566430963L;

	private byte rollNo;

	private String batch;

	private String section;

	public StudentAttendancePKId() {
		super();
	}

	public StudentAttendancePKId(byte rollNo, String batch, String section) {
		super();
		this.rollNo = rollNo;
		this.batch = batch;
		this.section = section;
	}

	@Override
	public int hashCode() {
		return Objects.hash(batch, rollNo, section);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StudentAttendancePKId other = (StudentAttendancePKId) obj;
		return Objects.equals(batch, other.batch) && rollNo == other.rollNo && Objects.equals(section, other.section);
	}

}
