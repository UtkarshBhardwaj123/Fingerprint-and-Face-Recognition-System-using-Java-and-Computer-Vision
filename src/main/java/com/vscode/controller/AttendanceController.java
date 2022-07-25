package com.vscode.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.attendance.service.AttendanceService;
import com.vscode.common.CriteriaType;
import com.vscode.common.Filter;
import com.vscode.common.JSONDynaBeanConverter;
import com.vscode.login.service.LoginService;

@RestController
public class AttendanceController {
	@Autowired
	AttendanceService attendanceServcie;

	@Autowired
	AttendanceService attendanceService;

	private JSONDynaBeanConverter converter = new JSONDynaBeanConverter();

	@GetMapping("/stdattendance")
	public String fillAttendance(@RequestBody String json) {
		try {
			JSONObject jsonObj = new JSONObject(json);
			// filtering data for a class attendance
			Filter filter = new Filter();
//			if (jsonObj.isNull(jsonObj.getString("CLASS")) || jsonObj.isNull(jsonObj.getString("SECTION")))
//				return json;

			filter.addCriteria("batch", CriteriaType.Equals, jsonObj.getString("batch"));
			filter.addCriteria("section", CriteriaType.Equals, jsonObj.getString("SECTION"));

			filter.addSelectColumn("name");
			filter.addSelectColumn("rollNo");
			filter.addSelectColumn("batch");
			filter.addSelectColumn("section");
			filter.addSelectColumn("totalDaysOpened");
			filter.addSelectColumn("totalDaysPresent");
			filter.addSelectColumn("totalDaysAbsent");
			filter.addSelectColumn("lastAttendanceDay");
			filter.addSelectColumn("studentStatus");

			return fillTable(attendanceService.loadAtt(filter)).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@PostMapping("/stdUpdate")
	public void updateRecords(@RequestBody String json) {

		try {
			JSONArray jsonObj = new JSONArray(json);
			converter.addToDynaBean(jsonObj);
			attendanceService.saveDynaBean(converter.getDynaBean());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@GetMapping("/stdDetails")

	public String fillDetails(String json) {
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			Filter filter = new Filter();

			// filter data for Student Attendance
			if (!jsonObj.isNull("NAME"))
				filter.addCriteria("name", CriteriaType.Equals, jsonObj.getString("NAME"));
			if (!jsonObj.isNull("ROLL_NO"))
				filter.addCriteria("rollNo", CriteriaType.Equals, jsonObj.getString("ROLL_NO"));
			if (!jsonObj.isNull("FATHERS_NAME"))
				filter.addCriteria("fathersName", CriteriaType.Equals, jsonObj.getString("FATHERS_NAME"));
			if (!jsonObj.isNull("MOTHERS_NAME"))
				filter.addCriteria("mothersName", CriteriaType.Equals, jsonObj.getString("MOTHERS_NAME"));
			if (!jsonObj.isNull("ADMISSION_NO"))
				filter.addCriteria("admissionNo", CriteriaType.Equals, jsonObj.getString("ADMISSION_NO"));
			if (!jsonObj.isNull("ADMISSION_DATE"))
				filter.addCriteria("admissionDate", CriteriaType.Equals, jsonObj.getString("ADMISSION_DATE"));
			if (!jsonObj.isNull("CLASS"))
				filter.addCriteria("class", CriteriaType.Equals, jsonObj.getString("CLASS"));
			if (!jsonObj.isNull("STD_STATUS"))
				filter.addCriteria("stdStatus", CriteriaType.Equals, jsonObj.getString("STD_STATUS"));

			return fillTable(attendanceService.loadDet(filter)).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;

	}

	private JSONArray fillTable(List<Map<String, Object>> dynaBean) {
		JSONArray jsonArray = new JSONArray();
		dynaBean.forEach(singleBean -> jsonArray.put(converter.convertDynaBeanToJSON(singleBean)));
		return jsonArray;
	}
}
