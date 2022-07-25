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

	public List<Map<String, Object>> loadDet(Filter filter);

	public void saveDynaBean(List<Map<String, Object>> dynaBean);

}
