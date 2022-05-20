package com.smartFarm.project.model.smartFarm;


import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.*;

import org.hibernate.annotations.ColumnDefault;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data //getter,setter,toString,defalt 자동생성
@Builder 
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Entity(name="smart_farm")
public class SmartFarmVo {
	
	@Id //JPA 어노테이션 아이디 인것을 인지함
	@Column(nullable = false, length = 30)
	@NonNull private String user_id; //아이디
	
	@Column(nullable = false, length = 30)
	@NonNull private String crop_species; //식물 종류
	
	@Column(nullable = false)
	@NonNull private int supply_water_timing; // 급수 간격
	
	@Column(nullable = false)
	@NonNull private int supply_medium_timing; // 배양액 급수 간격
	
	@Column(nullable = false)
	@NonNull private int supply_miticide_timing; // 살충제 급수 간격
	
	@Column(nullable = false)
	@NonNull private LocalTime light_up_timing; // 불켜는 시간
	
	@Column(nullable = false)
	@NonNull private LocalTime light_down_timing; // 불 끄는 시간
	
	@Column(nullable = false)
	private LocalDate plant_day=LocalDate.now(); // 심은 날짜
	
	@Column(nullable = false)
	@ColumnDefault("true")
	private boolean door_lock; // 문 잠김 여부
	
	@Column(nullable = false)
	@ColumnDefault("false")
	private boolean plant_plate; // 모종판 유무
	
	
}
