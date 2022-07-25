package com.vscode.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vscode.controller.LoginController;
import com.vscode.login.service.LoginService;
import com.vscode.login.service.LoginServiceImpl;

public class JSONDynaBeanConverter {

	private List<Map<String, Object>> dynaBean = new ArrayList<Map<String, Object>>();

//	private ObjectMapper objectMapper = new ObjectMapper();
	String column = null;

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
				this.column = column;
				if (CommonUtil.checkNull(jsonObj.getString(column)))
					continue;

				else if (isAdmin()) {

					continue;
				} else {

					data.put(column, jsonObj.get(column));
				}
			} catch (JSONException e) {
				System.out.print("");
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

	public void setDynaBean(List<Map<String, Object>> dynaBean) {
		this.dynaBean = dynaBean;
	}

	public void addToDynaBean(Map<String, Object> data) {
		dynaBean.add(data);
	}

	public boolean isAdmin() {
		return column.equals("isAdmin");
	}

}
