package com.kgate.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.entity.DailyReport;
import com.kgate.repository.DailyReportRepository;

@Service
@Transactional
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	DailyReportRepository dailyReportRepository;
	
	
	@Override
	public void saveReport(DailyReport dailyReport) {
		// TODO Auto-generated method stub
		dailyReportRepository.saveAndFlush(dailyReport);
	}


	@Override
	public List<DailyReport> getAllEmployee(String month, Integer year) {
		// TODO Auto-generated method stub
		return dailyReportRepository.getAllEmployee(month, year);
	}

}
