package com.vscode.controller;

import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.attendance.service.AttendanceService;
import com.vscode.common.CriteriaType;
import com.vscode.common.Filter;
import com.vscode.common.JSONDynaBeanConverter;

@RestController
@RequestMapping("/ac")
public class AttendanceController {
	@Autowired
	AttendanceService attendanceServcie;

	@Autowired
	AttendanceService attendanceService;

	private JSONDynaBeanConverter converter = new JSONDynaBeanConverter();

	private boolean allowedSensor = false;
	private boolean allowedUser = false;

	@GetMapping("/stdattendance")
	public String fillAttendance(@RequestBody String json) {
		try {
			JSONObject jsonObj = new JSONObject(json);
			// filtering data for a class attendance
			Filter filter = new Filter();
			if (jsonObj.isNull("CLASS") || jsonObj.isNull("SECTION"))
				return json;
			filter.addCriteria("CLASS", CriteriaType.Equals, jsonObj.getString("CLASS"));
			filter.addCriteria("SECTION", CriteriaType.Equals, jsonObj.getString("SECTION"));

			converter.setDynaBeans(attendanceService.loadAtt(filter));
			return fillTable(converter.getDynaBean()).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json;
	}

	@PostMapping("/scanFingerPrint")
	public String scanFingerPrint(@RequestBody String json) {
		JSONObject jsonObj = new JSONObject(json);
		if (!jsonObj.get("data").equals("0")) {
			try {
				Map<String, Object> dynaBean = processEmployeeData(jsonObj);

				if (dynaBean == null || dynaBean.isEmpty()) {
					return new JSONObject().put("EMPLOYEE_ID", "No data is provided from user side").toString();
				}

				return converter.convertDynaBeanToJSON(dynaBean).toString();
			} catch (JSONException e) {
				System.out.println(e);
				return new JSONObject().put("STATUS", "An error occured contact administrator").toString();
			}
		} else {
			if (allowedSensor) {
				allowedSensor = !allowedSensor;
				return new JSONObject().put("FINGERPRINT_ID", converter.getDynaBean().get(0).get("FINGERPRINT_ID"))
						.toString();
			}
			return new JSONObject().put("FINGERPRINT_ID", "0").toString();
		}
	}

	@GetMapping("/waitUser")
	public String waitUser() {
		if (!allowedUser) {
			return new JSONObject().put("STATUS", "false").toString();
		} else {
			allowedUser = false;
			return new JSONObject().put("STATUS", "true").toString();
		}
	}

	@PostMapping("/checkFingerprint")
	public String ckeckLoginIDAndPassword(@RequestBody String json) {
		JSONObject jsonObj = new JSONObject(json);

		if (jsonObj.get("FINGERPRINT_ID") != null || jsonObj.getInt("FINGERPRINT_ID") > 0) {
			Filter filter = new Filter();

			filter.addCriteria("FINGERPRINT_ID", CriteriaType.Equals, jsonObj.get("FINGERPRINT_ID").toString());

			List<Map<String, Object>> dynaBeans = attendanceService.loadEmpAtt(filter);

			if (dynaBeans == null || dynaBeans.isEmpty())
				return new JSONObject().put("STATUS", "false").toString();

			attendanceService.updateFingerPrint(dynaBeans.get(0));
			return new JSONObject().put("STATUS", "true").toString();

		}
		return new JSONObject().put("STATUS", "Send again").toString();
//		allowed = "1";
//		jsonObj.append("STATUS", String.valueOf(allowed));
//		return allowed;

	}

	@PutMapping("/scanFingerPrint")
	public void addFingerPrint() {
		try {
			attendanceService.addEmployee(converter.getDynaBean().get(0));
			allowedUser = true;
			// storing all employees working today
//			attendanceService.updateRecords(converter.convertJSONToDynaBeans(jsonObj), null);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//	@PostMapping("/checkFingerPrint")
//	public String updateAttendance(@RequestBody String json) {
//
//		try {
//
//			JSONObject jsonObj = new JSONObject(json);
//
//			if (jsonObj.get("FINGERPRINT_ID") != null || !jsonObj.get("FINGERPRINT_ID").equals("-1")) {
//				Filter filter = new Filter();
//
//				filter.addCriteria("FINGERPRINT_ID", CriteriaType.Equals, jsonObj.get("FINGERPRINT_ID").toString());
//
//				attendanceService.updateFingerPrint(attendanceService.loadEmpAtt(filter).get(0));
//				return "done";
//			}
//
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//		return "Send again";
//	}

	@PostMapping("/stdUpdate")
	public void updateRecords(@RequestBody String json) {

		try {
			JSONObject jsonObj = new JSONObject(json);
			// storing all the records of students in a class
			List<Map<String, Object>> allDynaBeans = converter.getDynaBean();

			// storing all the records of student present today in the class
			converter.setDynaBeans(converter.getJSONArray(jsonObj, "DATA"));
			attendanceService.updateRecords(converter.getDynaBean(), allDynaBeans);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/stdDetails")
	public String fillDetails(@RequestBody String json) {
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			Filter filter = new Filter();

			// filter data for Student Attendance
			if (!jsonObj.isNull("NAME"))
				filter.addCriteria("NAME", CriteriaType.Equals, jsonObj.getString("NAME"));
			if (!jsonObj.isNull("ROLL_NO"))
				filter.addCriteria("ROLL_NO", CriteriaType.Equals, jsonObj.getString("ROLL_NO"));
			if (!jsonObj.isNull("FATHERS_NAME"))
				filter.addCriteria("FATHERS_NAME", CriteriaType.Equals, jsonObj.getString("FATHERS_NAME"));
			if (!jsonObj.isNull("MOTHERS_NAME"))
				filter.addCriteria("MOTHERS_NAME", CriteriaType.Equals, jsonObj.getString("MOTHERS_NAME"));
			if (!jsonObj.isNull("ADMISSION_NO"))
				filter.addCriteria("ADMISSION_NO", CriteriaType.Equals, jsonObj.getString("ADMISSION_NO"));
			if (!jsonObj.isNull("ADMISSION_DATE"))
				filter.addCriteria("ADMISSION_DATE", CriteriaType.Equals, jsonObj.getString("ADMISSION_DATE"));
			if (!jsonObj.isNull("CLASS"))
				filter.addCriteria("CLASS", CriteriaType.Equals, jsonObj.getString("CLASS"));
			if (!jsonObj.isNull("STDUDENT_STATUS"))
				filter.addCriteria("STDUDENT_STATUS", CriteriaType.Equals, jsonObj.getString("STDUDENT_STATUS"));

			return fillTable(attendanceService.loadDett(filter)).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "{'  '}";

	}

	@GetMapping("/empAtt")
	public String loadAttendance() {
		try {

			Filter filter = new Filter();

			return fillTable(attendanceService.loadEmpDett(filter)).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return "{'  '}";

	}

	private Map<String, Object> processEmployeeData(JSONObject jsonObj) {

		Map<String, Object> dynaBean = attendanceService.idsAvailable();

		if (dynaBean == null || dynaBean.isEmpty())
			return null;

		if (((Byte) dynaBean.get("FINGERPRINT_ID")) < 0) {
			dynaBean.clear();
			dynaBean.put("STATUS", "No more Fingerprint ids can be stored");
			return dynaBean;
		}

		converter.addToDynaBean(jsonObj.getJSONObject("data"));
		converter.getDynaBean().forEach(in -> {
			in.put("EMPLOYEE_ID", dynaBean.get("EMPLOYEE_ID"));
			in.put("FINGERPRINT_ID", dynaBean.get("FINGERPRINT_ID"));
		});

		dynaBean.remove("FINGERPRINT_ID");

		// signals fingerprint sensor to store fingerprint
		allowedSensor = true;

		return dynaBean;
	}

	private JSONObject fillTable(List<Map<String, Object>> dynaBean) {
		JSONObject jsonObject = new JSONObject();
		try {
			jsonObject.put("DATA", converter.convertDynaBeansToJSONArray(dynaBean));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

	@GetMapping("/empDetails")
	public String recognizeFace(@RequestBody String json) {
		JSONObject jsonObj = new JSONObject(json);
		return converter
				.convertDynaBeanToJSON(attendanceService.recognizeFace(converter.convertJSONToDynaBeans(jsonObj)))
				.toString();

	}

//	@PostMapping("/registerFace")
//	public void registerFace(@RequestBody String json) {
//		JSONObject obj = new JSONObject(json);
//		if (obj.isNull("IMAGE"))
//			return;
//		else {
//			attendanceService.saveImage(obj);
//		}
//	}
}
