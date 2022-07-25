//package com.vscode.common;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//
//import com.vscode.entity.attendance.StudentAttendanceBean;
//
//public class DynaBeanEntityConverter {
//
//	public List<StudentAttendanceBean> convertAttendanceToJSON(List<Map<String, Object>> dynaBean,StudentAttendanceBean entity) {
//		List<StudentAttendanceBean> entityList = new ArrayList<StudentAttendanceBean>();
//		for (Map<String, Object> map : dynaBean) {
//			entity.setBatch(map.get("CLASS").toString());
//			entity.setName(map.get("NAME").toString());
//			entityList.add(entity);
//		}
//		return entityList;
//	}
//
//	public void convertDynaBeanToJSON(Object object, Object object2) {
//		
//	}
//
//}
