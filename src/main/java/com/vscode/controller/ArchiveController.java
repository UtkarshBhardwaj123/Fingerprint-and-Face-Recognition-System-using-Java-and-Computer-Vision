package com.vscode.controller;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vscode.archive.service.ArchiveService;

@RestController
public class ArchiveController {
	
	@Autowired
	private ArchiveService archiveService;
	
//	@GetMapping("/deletestd")
//	public String deleteAndArchiveStudent(String json) {
//		try {
//			JSONObject jsonObj = new JSONObject(json);
//			delteStudent(jsonObj);
//			archiveService.deleteById();
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//	}

	private void delteStudent(JSONObject jsonObj) {
		
	}

}
