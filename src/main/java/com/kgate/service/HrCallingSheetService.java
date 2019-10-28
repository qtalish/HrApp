package com.kgate.service;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.User;


public interface HrCallingSheetService {
	
	public void saveCallingSheet(HrCallingSheet hrCallingSheet);

	/*List<HrCallingSheet> findByUpdatedDateAndEmpCode(Date date,String empCode);*/
	
	List<HrCallingSheet> findAllCandidate(String empCode);
	
	public Page<HrCallingSheet> findEmployeePage(Pageable pageable,String empCode);
	
	public void deleteById(int id);
	
	public HrCallingSheet findCandidateById(int id);
	
	public Page<HrCallingSheet> searchCandidate(Pageable pageable, String txt);
	
	
}
