package com.vscode.entity.attendance;

import java.io.Serializable;
import java.util.Objects;

public class EmployeeAttendancePKID implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUId = -6912428361684511101L;

	private byte fingerPrintId;
	private short employeeId;

	public EmployeeAttendancePKID() {

	}

	public EmployeeAttendancePKID(byte fingerPrintId, short employeeId) {
		super();
		this.fingerPrintId = fingerPrintId;
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeId, fingerPrintId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeAttendancePKID other = (EmployeeAttendancePKID) obj;
		return Objects.equals(employeeId, other.employeeId) && Objects.equals(fingerPrintId, other.fingerPrintId);
	}

}
