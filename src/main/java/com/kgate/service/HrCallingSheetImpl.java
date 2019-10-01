package com.kgate.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.HrDailyReport;
import com.kgate.repository.HrCallingSheetRepository;

@Service
public class HrCallingSheetImpl implements HrCallingSheetService {

	@Autowired
	HrCallingSheetRepository hrCallingSheetRepository;
	
	@Override
	public void saveReport(HrCallingSheet hrCallingSheet) {
		// TODO Auto-generated method stub
		hrCallingSheetRepository.save(hrCallingSheet);
	}

	@Override
	public List<HrCallingSheet> findByUpdatedDateAndEmpCode(Date date,String empCode) {
		// TODO Auto-generated method stub
		return hrCallingSheetRepository.findByUpdatedDateAndEmpCode(date,empCode);
	}

}
