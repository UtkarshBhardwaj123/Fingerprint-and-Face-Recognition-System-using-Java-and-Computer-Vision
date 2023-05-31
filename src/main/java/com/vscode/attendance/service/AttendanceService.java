package com.vscode.attendance.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.vscode.common.Filter;

/**
 * @author Utkarsh Bhardwaj
 */
@Service
public interface AttendanceService {

	public List<Map<String, Object>> loadAtt(Filter filter);

	List<Map<String, Object>> loadDett(Filter filter);

	public void updateRecords(List<Map<String, Object>> attendedDynaBeans, List<Map<String, Object>> allDynaBeans);

	public void addEmployee(Map<String, Object> convertJSONToDynaBeans);

	public void updateFingerPrint(Map<String, Object> convertJSONToDynaBeans);

	List<Map<String, Object>> loadEmpAtt(Filter filter);

	public Map<String, Object> idsAvailable();

	Map<String, Object> recognizeFace(Map<String, Object> dynaBean);
	
	public List<Map<String, Object>> loadEmpDett(Filter filter);

}
