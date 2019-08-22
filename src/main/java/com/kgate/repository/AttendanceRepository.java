package com.kgate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.kgate.entity.Attendence;

public interface AttendanceRepository extends JpaRepository<Attendence, Integer>{

	@Query("select att from Attendence att")
	List<Attendence> getAttendance();
}