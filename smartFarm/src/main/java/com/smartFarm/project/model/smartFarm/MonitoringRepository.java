package com.smartFarm.project.model.smartFarm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MonitoringRepository extends JpaRepository<MonitoringVo, MonitoringVoId> {

	List<MonitoringVo> findAll();

	// SQL 일반 파라미터 쿼리, @Param 사용 O
	@Query(value = "select * from monitoring where device_code = :device_code", nativeQuery = true)
	public List<MonitoringVo> findByDeviceCode(@Param(value = "device_code") String device_code);
}
