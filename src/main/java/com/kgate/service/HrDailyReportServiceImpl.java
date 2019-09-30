package com.kgate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.entity.HrDailyReport;
import com.kgate.repository.HrDailyReportRepository;

@Service
public class HrDailyReportServiceImpl implements HrDailyReportService {

	@Autowired
	HrDailyReportRepository hrDailyReportRepository;

	@Override
	public void saveReport(HrDailyReport dailyReport) {
		// TODO Auto-generated method stub
		hrDailyReportRepository.save(dailyReport);
	}

	@Override
	public List<HrDailyReport> getAllHr(String month, Integer year) {
		// TODO Auto-generated method stub
		return hrDailyReportRepository.getAllHr(month, year);
	}

}
