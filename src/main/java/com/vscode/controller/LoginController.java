package com.vscode.controller;

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

/**
 * @author Utkarsh Bhardwaj
 */

@RestController
public class LoginController {

	@Autowired
	LoginService loginService;

	private JSONDynaBeanConverter converter = new JSONDynaBeanConverter();;

	@GetMapping("/checkLoginIDAndPassword")
	public boolean ckeckLoginIDAndPassword(@RequestBody String jsonStr) {

		try {
			if (jsonStr != null) {
				JSONObject jsonObj = new JSONObject(jsonStr);
				converter.addToDynaBean(loginService, jsonObj);
				
				return loginService.checkLoginID(converter.getDynaBean(), loginService.isAdmin());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return false;

	}

	@PostMapping("/addUser")
	public void addUserOrAdmin(@RequestBody String jsonStr) throws JSONException {
		JSONObject jsonObject = new JSONObject(jsonStr);
		converter.convertJSONToDynaBeans(jsonObject);
		loginService.saveDynaBean(converter.getDynaBean(), loginService.isAdmin());
	}

	// J
//	public boolean isAdmin() {
//		return isAdmin;
//	}

//	private void addToLoginDynaBean(JSONArray jsonArray) throws JSONException {
//
//		for (int i = 0; i < jsonArray.length(); i++) {
//			JSONObject jsonObj = jsonArray.getJSONObject(i);
//
//			Map<String, Object> data = converter.convertJSONToDynaBeans(jsonObj);
//			isAdmin = converter.isAdmin();
//
//			if (data != null && !data.isEmpty())
//				converter.addToDynaBean(data);
//		}
//	}
}
