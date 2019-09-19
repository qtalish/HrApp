package com.kgate.service;

import java.util.List;


import com.kgate.entity.DailyReport;

public interface DailyReportService {
	
	public void saveReport(DailyReport dailyReport);
	
	public List<DailyReport> getAllEmployee(String month,Integer year);

}
