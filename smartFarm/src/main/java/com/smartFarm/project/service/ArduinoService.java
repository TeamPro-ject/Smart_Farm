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
				 .firstWaterTankLevel(param.get("firstdistance").toString())
				 .secondWaterTankLevel(param.get("secondDistance").toString())
				 .thirdWaterTankLevel(param.get("thirdDistance").toString())
				 .monitoringHumidity(param.get("humid").toString())
				 .monitoringTemperature(param.get("tempC").toString())
				 .magneticValue(param.get("magneticValue").toString())
				 .monitoringIlluminance(param.get("illuminanceValue").toString())
				 .build();
		 
		log.info(mvo.getMonitoringTime());	
		MonitoringVo mvos=monitoringRepository.save(mvo);
		monitoringRepository.flush();
		log.info(mvo.toString());
		log.info("개수"+monitoringRepository.count());
		 
	}

}
