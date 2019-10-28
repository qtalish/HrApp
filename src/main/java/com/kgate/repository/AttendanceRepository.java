package com.kgate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.Attendance;

@Repository
@Transactional
public interface AttendanceRepository extends JpaRepository<Attendance, Integer> {

	@Query(value = "select * from Attendance a where a.attDate=:date order by a.firstName", nativeQuery = true)
	List<Attendance> getAttendance(@Param("date") Date date);

	@Modifying
	@Query(value = "update Attendance a set a.status=:status where a.id=:id", nativeQuery = true)
	void updateStatus(@Param("id") Integer id, @Param("status") String status);

//	update remarks
	@Modifying
	@Query(value = "update Attendance a set a.remarks=:remarks where a.id=:id", nativeQuery = true)
	void updateRemarks(@Param("id") Integer id, @Param("remarks") String remarks);

	@Query(value = "select * from Attendance a where a.attDate=:date", nativeQuery = true)
	List<Attendance> findAttendanceDate(@Param("date") Date date);

	@Query("select att from Attendance att")
	List<Attendance> getAttendance();
	
	@Query("SELECT c FROM Attendance c WHERE c.empCode=:empCode")
	Attendance findByeCode(@Param("empCode") String empCode);
}