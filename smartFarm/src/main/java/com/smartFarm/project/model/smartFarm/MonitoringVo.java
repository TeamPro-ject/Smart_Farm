package com.smartFarm.project.model.smartFarm;

import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@Builder(builderMethodName = "monitoringVoBuilder")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@IdClass(MonitoringVoId.class)
@Entity(name = "monitoring")
public class MonitoringVo {
	
	@Id
	@Column(nullable = false, length = 30) // column의 조건
	@NonNull
	private String device_code;
	
	@Id
	@Builder.Default
	@Column(nullable = false)
	private LocalDateTime monitoring_time=LocalDateTime.now(); // 모니터링 시간
	
	@Column( length = 10)
	private String monitoring_temperature; // 온도
	
	@Column( length = 10)
	private String monitoring_humidity; // 습도
	
	@Column( length = 10)
	private String monitoring_illuminance; // 조도
	
	@Column( length = 10)
	private String first_water_tank_level; //  1 물탱크 수위
	
	@Column( length = 10)
	private String second_water_tank_level; // 2 물탱크 수위
	
	@Column( length = 10)
	private String third_water_tank_level; // 3 물탱크 수위
	
	@Column( length = 3)
	private String magneticValue; // 자석센서
	
	public static MonitoringVoBuilder builder(String device_code) {
        if(device_code == null) {
            throw new IllegalArgumentException("필수 파라미터 누락");
        }
        return monitoringVoBuilder().device_code(device_code);
    }

}
