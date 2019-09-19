package com.kgate.service;

import java.util.List;

import com.kgate.entity.Attendance;
import com.kgate.entity.Salary;
import com.kgate.entity.User;

public interface SalaryService {
	
	public Salary saveSalary(Salary salary);
	
	public Salary findByEmpCode(String empCode,String month,Integer year);

	public List<String> getEmployeeList();

	public List<Attendance> getAllEmployee(String month,Integer year);

	public Salary findSalary(String empCode, String month, Integer year);

}
