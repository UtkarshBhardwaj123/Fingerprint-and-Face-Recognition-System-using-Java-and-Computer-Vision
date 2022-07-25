package com.vscode.common.repo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.vscode.entity.attendance.StudentAttendanceBean;
import com.vscode.entity.login.Login;

public class CommonServiceImpl implements CommonService {
	public void convertDynaBeanToStdAttendance(List<Map<String, Object>> dynaBean,StudentAttendanceBean entity) {
		List<StudentAttendanceBean> entityList = new ArrayList<StudentAttendanceBean>();
		for (Map<String, Object> map : dynaBean) {
			entity.setBatch(map.get("CLASS").toString());
			entity.setName(map.get("NAME").toString());
			entityList.add(entity);
		}
	
	}

}
