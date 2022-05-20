package com.smartFarm.project.model.smartFarm;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MonitoringVoId implements Serializable  {
	
	@EqualsAndHashCode.Include
	private String device_code;
	
	@EqualsAndHashCode.Include
	private LocalDateTime monitoring_time=LocalDateTime.now(); // 모니터링 시간
	
	
	
}
