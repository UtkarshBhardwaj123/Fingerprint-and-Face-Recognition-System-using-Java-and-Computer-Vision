package com.vscode.entity.archive;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.vscode.entity.attendance.StudentDetails;

/**
 * @author Utkarsh Bhardwaj 
 */

@Entity
@Table(name = "STUDENT_ARCHIVE")
public class StudentArchiveBean extends StudentDetails {
	
	@Column(name="REMARKS")
	private String remarks;
	
	@Column(name="REASON")
	private String reason;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
