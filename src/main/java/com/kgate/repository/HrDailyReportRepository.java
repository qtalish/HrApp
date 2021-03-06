package com.kgate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.HrDailyReport;

@Repository
public interface HrDailyReportRepository extends JpaRepository<HrDailyReport, Integer> {

	@Query(value = "select hdr from HrDailyReport hdr where hdr.month=:month and hdr.year=:year")
	public List<HrDailyReport> getAllHr(@Param("month") String month, @Param("year") Integer year);
	
	
	@Query(value= "SELECT dr from HrDailyReport dr where dr.date=:date and dr.empCode=:empCode")
	public HrDailyReport getTodaysTask(@Param("date") Date date,@Param("empCode") String empCode);

}
