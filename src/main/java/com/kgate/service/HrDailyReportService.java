package com.kgate.service;

import java.util.List;

import com.kgate.entity.HrDailyReport;

public interface HrDailyReportService {

	public void saveReport(HrDailyReport dailyReport);

	public List<HrDailyReport> getAllHr(String month, Integer year);

}
