package com.smartFarm.project.model.smartFarm;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitoringRepository  extends JpaRepository<MonitoringVo, MonitoringVoId> {
	
	List<MonitoringVo> findAll();
}
