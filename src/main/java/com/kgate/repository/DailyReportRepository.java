package com.kgate.repository;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.Attendance;
import com.kgate.entity.DailyReport;

@Repository
@Transactional
public interface DailyReportRepository extends JpaRepository<DailyReport,Integer>   
{
	
	@Query(value = "select dr from DailyReport dr where dr.month=:month and dr.year=:year")
	public List<DailyReport> getAllEmployee(@Param("month") String month, @Param("year") Integer year);
	
	
	@Query(value = "SELECT dr.taskForTomorrow from DailyReport dr where dr.date=:date and dr.empCode=:empCode")
	public String getPreviousTask(@Param("date") Date date,@Param("empCode") String empCode);
	
	
	@Query(value= "SELECT dr from DailyReport dr where dr.date=:date and dr.empCode=:empCode")
	public DailyReport getTodaysTask(@Param("date") Date date,@Param("empCode") String empCode);

}
