package com.kgate.repository;

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

}
