package com.kgate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.UserLeaves;
import com.kgate.repository.LeavesRepository;
import com.kgate.repository.SalaryRepository;
import com.kgate.repository.UserRepository;

@Service
@Transactional
public class LeavesServiceImpl implements LeavesService {
	
	@Autowired
	LeavesRepository leavesRepository;
	

	@Override
	public UserLeaves getLeavesDetails(String empCode, String month, Integer year) {
		// TODO Auto-generated method stub
		return leavesRepository.getLeavesDetails(empCode, month, year)   ;
	}


	@Override
	public UserLeaves getPreviousLeaves(String empc,String monthLeaves,Integer year) {
		// TODO Auto-generated method stub
		UserLeaves userLeaves=leavesRepository.getPreviousLeaves(empc,monthLeaves,year);
		return userLeaves;
	}

	@Override
	public UserLeaves getBalanceLeave(String empCode, String month, int year) {
		// TODO Auto-generated method stub
		return leavesRepository.getBalanceLeave(empCode, month, year);
	}

}
