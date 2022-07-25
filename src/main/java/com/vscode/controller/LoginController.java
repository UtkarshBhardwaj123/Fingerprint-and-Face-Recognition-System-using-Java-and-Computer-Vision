package com.vscode.controller;

import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.common.JSONDynaBeanConverter;
import com.vscode.login.service.LoginService;
import com.vscode.login.service.LoginServiceImpl;

/**
 * @author Utkarsh Bhardwaj
 */

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	private JSONDynaBeanConverter converter = new JSONDynaBeanConverter();;

	private boolean isAdmin = false;

	@GetMapping("/checkLoginIDAndPassword")
	public boolean ckeckLoginIDAndPassword(@RequestBody String jsonStr) {

		try {
			if (jsonStr != null) {
				JSONArray jsonArray = new JSONArray(jsonStr);
				addToLoginDynaBean(jsonArray);
				return loginService.checkLoginID(converter.getDynaBean(), isAdmin);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;

	}

	@PostMapping("/addUser")
	public void addUserOrAdmin(@RequestBody String jsonStr, boolean isAdmin) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonStr);
		converter.convertJSONToDynaBeans(jsonObject);
		loginService.saveDynaBean(converter.getDynaBean(), isAdmin);
	}

	// J
//	public boolean isAdmin() {
//		return isAdmin;
//	}
	private void addToLoginDynaBean(JSONArray jsonArray) throws JSONException {

		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject jsonObj = jsonArray.getJSONObject(i);
			Map<String, Object> data = converter.convertJSONToDynaBeans(jsonObj);
			isAdmin = converter.isAdmin();
			if (data != null && !data.isEmpty())
				converter.addToDynaBean(data);
		}
	}
}
