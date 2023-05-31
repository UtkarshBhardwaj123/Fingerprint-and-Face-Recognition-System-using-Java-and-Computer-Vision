package com.vscode.common;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.nd4j.linalg.factory.Nd4j;

import com.fasterxml.jackson.databind.ObjectReader;
import com.vscode.login.service.LoginService;
import com.vscode.recognizeface.InputVector;

public class JSONDynaBeanConverter {

	private List<Map<String, Object>> dynaBean = new ArrayList<Map<String, Object>>();

//	private ObjectMapper objectMapper = new ObjectMapper();
	private LoginService loginService;

	public Map<String, Object> convertJSONToDynaBeans(JSONObject jsonObj) {

		if (jsonObj == null)
			return null;

		String[] columns = JSONObject.getNames(jsonObj);
		if (columns == null || columns.length == 0)
			return null;

		// converting JSON Object to Map<String, Object>(DynaBean)
		Map<String, Object> data = new HashMap<String, Object>();
		for (String column : columns) {
			try {
				if (CommonUtil.checkNull(jsonObj.getString(column)))
					continue;

				else if (column.equalsIgnoreCase("isAdmin"))

					loginService.setAdmin(Boolean.parseBoolean(jsonObj.getString(column)));
				else if (column.equalsIgnoreCase("saveAdmin")) {

					loginService.setSaveAdmin(Boolean.parseBoolean(jsonObj.getString(column)));
				} else if (column.equalsIgnoreCase("EMPLOYEE_IMAGE")) {
//					ObjectReader fr = new ObjectReader();
					byte[] imageArray = Base64.getDecoder().decode(jsonObj.get("EMPLOYEE_IMAGE").toString().trim());
					data.put(column, imageArray);

//					data.put("INPUT_VECTOR",
//							new InputVector().createInputVector(ImageIO.read(new ByteArrayInputStream(imageArray))));
				} else {

					data.put(column, jsonObj.get(column));
				}
			} catch (JSONException e) {
				System.out.print("JSON Exception");
			} 
		}
		return data;

//			dynaBean.add(objectMapper.readValue(jsonObj.toString(), new TypeReference<Map<String, Object>>() {
//			}));
	}

	public void addToDynaBean(JSONArray jsonArray) throws JSONException {

		for (int i = 0; i < jsonArray.length(); i++) {
			Map<String, Object> data = convertJSONToDynaBeans(jsonArray.getJSONObject(i));
			if (data != null && !data.isEmpty())

				addToDynaBean(data);
		}
	}

	public JSONObject convertDynaBeanToJSON(Map<String, Object> singleBean) {
		JSONObject jsonObj = new JSONObject();
		if (singleBean == null)
			return jsonObj;

		singleBean.forEach((column, value) -> {
			try {
				jsonObj.put(column, CommonUtil.getCleanData(value));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		});

		return jsonObj;
	}

	public Object getJsonObject(JSONObject input, String key) {
		try {
			return input.get(key);
		} catch (JSONException e) {
			return null;
		}
	}

	public List<Map<String, Object>> getDynaBean() {
		return dynaBean;
	}

	public void setDynaBeans(List<Map<String, Object>> dynaBean) {
		this.dynaBean = dynaBean;
	}

	public void setDynaBeans(JSONArray jsonArray) throws JSONException {
		List<Map<String, Object>> dynaBeans = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < jsonArray.length(); i++) {
			Map<String, Object> data = convertJSONToDynaBeans(jsonArray.getJSONObject(i));
			if (data != null && !data.isEmpty())

				dynaBeans.add(data);
		}
		setDynaBeans(dynaBeans);
	}

	public void addToDynaBean(Map<String, Object> data) {
		dynaBean.add(data);
	}

	public void addToDynaBean(LoginService loginService, JSONObject input) {
		if (input == null)
			return;
		this.loginService = loginService;
		addToDynaBean(convertJSONToDynaBeans(input));

	}

	public JSONArray convertDynaBeansToJSONArray(List<Map<String, Object>> dynaBeans) {
		JSONArray jsonArray = new JSONArray();
		if (dynaBeans != null && !dynaBeans.isEmpty())
			dynaBeans.forEach(dynaBean -> jsonArray.put(convertDynaBeanToJSON(dynaBean)));
		return jsonArray;
	}

	public JSONArray getJSONArray(JSONObject input, String key) throws JSONException {
		return input.getJSONArray(key);

	}

	public void addToDynaBean(JSONObject jsonObj) {
		addToDynaBean(convertJSONToDynaBeans(jsonObj));
	}
}
