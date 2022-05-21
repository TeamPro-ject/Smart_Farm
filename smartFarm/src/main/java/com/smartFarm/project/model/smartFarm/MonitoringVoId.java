package com.smartFarm.project.model.smartFarm;

import java.io.Serializable;
import java.time.LocalDateTime;


import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NoArgsConstructor
public class MonitoringVoId implements Serializable  {
	
	@EqualsAndHashCode.Include
	private String deviceCode;
	
	@EqualsAndHashCode.Include
	private LocalDateTime monitoringTime=LocalDateTime.now(); // 모니터링 시간
	
	
	
}
