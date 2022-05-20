package com.smartFarm.project.controller;


import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartFarm.project.service.ArduinoService;
import com.smartFarm.project.service.SmartFarmService;

import lombok.extern.log4j.Log4j2;

@Controller
@Log4j2
public class ArduinoController {
	@Autowired
	ArduinoService arduinoService;
	
	@RequestMapping(value = "/arduino")
	public void insertSensingData( @RequestBody HashMap<String, Object> param ) {
		log.info(param.size())	;
		log.info(param.toString());
		arduinoService.insertSensingData(param);
		
	}
}
