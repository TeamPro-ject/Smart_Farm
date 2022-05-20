package com.smartFarm.project.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.ModelAndView;

import com.smartFarm.project.controller.ArduinoController;
import com.smartFarm.project.model.smartFarm.MonitoringRepository;
import com.smartFarm.project.model.smartFarm.MonitoringVo;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ArduinoService {

	@Autowired
	MonitoringRepository monitoringRepository;

	public void insertSensingData(HashMap<String, Object> param) {	
		 MonitoringVo mvo = MonitoringVo.builder(param.get("device").toString())
				 .first_water_tank_level(param.get("firstdistance").toString())
				 .second_water_tank_level(param.get("secondDistance").toString())
				 .third_water_tank_level(param.get("thirdDistance").toString())
				 .monitoring_humidity(param.get("humid").toString())
				 .monitoring_temperature(param.get("tempC").toString())
				 .monitoring_humidity(param.get("humid").toString())
				 .magneticValue(param.get("magneticValue").toString())
				 .monitoring_illuminance(param.get("illuminanceValue").toString())
				 .build();
		 
		
		MonitoringVo mvos=monitoringRepository.save(mvo);
		monitoringRepository.flush();
		log.info(mvo.toString());
		log.info("개수"+monitoringRepository.count());
		 
	}

}
