package com.kgate.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.HrDailyReport;
import com.kgate.repository.HrCallingSheetRepository;

@Service
public class HrCallingSheetImpl implements HrCallingSheetService {

	@Autowired
	HrCallingSheetRepository hrCallingSheetRepository;

	@Override
	public void saveCallingSheet(HrCallingSheet hrCallingSheet) {
		// TODO Auto-generated method stub
		
		hrCallingSheetRepository.save(hrCallingSheet);
				
	}

	@Override
	public List<HrCallingSheet> findAllCandidate(String empCode) {
		// TODO Auto-generated method stub
		return hrCallingSheetRepository.findByEmpCode(empCode) ;
	}

	@Override
	public Page<HrCallingSheet> findEmployeePage(Pageable pageable, String empCode) {
		// TODO Auto-generated method stub
		return hrCallingSheetRepository.findCandidatePage(pageable, empCode);
	}

	@Override
	public void deleteById(int id) {
		// TODO Auto-generated method stub
		hrCallingSheetRepository.deleteById(id);
		
	}

	@Override
	public HrCallingSheet findCandidateById(int id) {
		// TODO Auto-generated method stub
		return hrCallingSheetRepository.getOne(id);
	}

	@Override
	public Page<HrCallingSheet> searchCandidate(Pageable pageable, String txt) {
		// TODO Auto-generated method stub
		return hrCallingSheetRepository.searchCandidate(pageable, txt);
	}

	
	


}
