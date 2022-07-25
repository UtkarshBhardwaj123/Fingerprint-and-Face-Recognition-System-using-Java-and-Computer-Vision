package com.vscode.attendance.service;

import com.vscode.common.repo.DefaultRepo;
import com.vscode.entity.attendance.StudentAttendanceBean;
import com.vscode.entity.attendance.StudentAttendancePKId;

/**
 * @author Utkarsh Bhardwaj 
 */

public interface StudentAttendanceRepo extends  DefaultRepo<StudentAttendanceBean, StudentAttendancePKId> {

}
