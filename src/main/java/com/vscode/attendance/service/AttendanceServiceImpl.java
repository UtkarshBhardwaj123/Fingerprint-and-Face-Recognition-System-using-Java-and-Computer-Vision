package com.vscode.attendance.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.vscode.common.Filter;
import com.vscode.common.repo.DefaultRepo;
import com.vscode.entity.attendance.StudentAttendanceBean;
import com.vscode.entity.attendance.StudentAttendancePKId;
import com.vscode.entity.attendance.StudentDetailsBean;
import com.vscode.entity.login.Login;
import com.vscode.entity.login.LoginAdminBean;
import com.vscode.entity.login.LoginUserBean;

/**
 * @author Utkarsh Bhardwaj
 */
@Component
@Service
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private StudentAttendanceRepo studentAttendanceRepo;

	@Autowired
	private StudentDetailsRepo studentDetailsRepo;

	@Override
	public List<Map<String, Object>> loadAtt(Filter filter) {
		return studentAttendanceRepo.loadAll(filter);
	}

	@Override
	public List<Map<String, Object>> loadDet(Filter filter) {
		return studentDetailsRepo.loadAll(filter);
	}

	@Override
	public void saveDynaBean(List<Map<String, Object>> dynaBean) {
		StudentAttendanceBean stdAttendance = null;
		if (dynaBean != null) {
			stdAttendance = new StudentAttendanceBean();
			studentAttendanceRepo.saveAll(
					(Iterable<StudentAttendanceBean>) convertDynaBeanToAttEntity(dynaBean, stdAttendance).iterator());
		}

	}

	private List<StudentAttendanceBean> convertDynaBeanToAttEntity(List<Map<String, Object>> dynaBean,
			StudentAttendanceBean entity) {
		List<StudentAttendanceBean> entityList = new ArrayList<StudentAttendanceBean>();

		for (Map<String, Object> map : dynaBean) {

			entity.setName(map.get("NAME").toString());
			entity.setrollNo(Byte.parseByte(map.get("ROLL_NO").toString()));
			entity.setBatch(map.get("CLASS").toString());
			entity.setSection(map.get("SECTION").toString());
			entity.setStudentStatus(map.get("STUDENT_STATUS").toString());
			entity.setTotalDaysAbsent(Integer.parseInt(map.get("TOTAL_DAYS_ABSENT").toString()));
			entity.setTotalDaysPresent(Integer.parseInt(map.get("TOTAL_DAYS_PRESENT").toString()));
			entity.settotalDaysOpened(Integer.parseInt(map.get("TOTAL_DAYS_OPENED").toString()));

			entityList.add(entity);
		}

		return entityList;
	}

	private List<StudentDetailsBean> convertDynaBeanToAttEntity(List<Map<String, Object>> dynaBean,
			StudentDetailsBean entity) {
		List<StudentDetailsBean> entityList = new ArrayList<StudentDetailsBean>();

		for (Map<String, Object> map : dynaBean) {

			entity.setName(map.get("NAME").toString());
			entity.setRollNo(Byte.parseByte(map.get("ROLL_NO").toString()));
			entity.setBatch(map.get("CLASS").toString());
			entity.setSection(map.get("SECTION").toString());
			entity.setStdStatus(map.get("STUDENT_STATUS").toString());
			entity.setFathersName(map.get("FATHERS_NAME").toString());
			entity.setMothersName(map.get("MOTHERS_NAME").toString());
			entity.setAdmissionNo(BigInteger.valueOf((Long) map.get("ADMISSION_NO")));
			entity.setAdmissionDate(Date.valueOf(map.get("ADMISSION_DATE").toString()));
			entityList.add(entity);
		}

		return entityList;
	}


}
