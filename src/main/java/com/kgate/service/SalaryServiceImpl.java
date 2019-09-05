package com.kgate.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.Attendance;
import com.kgate.entity.Salary;
import com.kgate.entity.User;
import com.kgate.repository.SalaryRepository;

@Service
@Transactional
public class SalaryServiceImpl implements SalaryService {

	@Autowired
	SalaryRepository salaryRepository;

	@Override
	public Salary saveSalary(Salary salary) {
		// TODO Auto-generated method stub
		return salaryRepository.save(salary);
	}

	@Override
	public Salary findByEmpCode(String empCode,String month,Integer year) {
		// TODO Auto-generated method stub
		return salaryRepository.findByEmpCode(empCode,month,year);
	}

	@Override
	public List<String> getEmployeeList() {
		// TODO Auto-generated method stub
		
		List<String> list=salaryRepository.getEmployeeList();
		return list;
	}

	@Override
	public List<Attendance> getAllEmployee(String month,Integer year) {
		// TODO Auto-generated method stub
		List<Attendance> user=salaryRepository.getAllEmployee(month,year);
		return user;
	}

}
