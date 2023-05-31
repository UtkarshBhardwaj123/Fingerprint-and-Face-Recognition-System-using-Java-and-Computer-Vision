package com.vscode.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.archive.service.ArchiveService;
//Currently in working
@RestController
public class ArchiveController {

	@Autowired
	private ArchiveService archiveService;

	@DeleteMapping("/deletestd")
	public String deleteAndArchiveStudent(String json) {
		try {
			JSONObject jsonObj = new JSONObject(json);
			deleteStudent(jsonObj);
			archiveService.deleteBulk();
			return null;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return new String();
	}

	private void deleteStudent(JSONObject jsonObj) {

	}

}
