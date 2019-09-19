package com.kgate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.Attendance;
import com.kgate.entity.Salary;
import com.kgate.entity.User;

@Repository
@Transactional
public interface SalaryRepository extends JpaRepository<Salary, Integer> {

	@Query(value = "select * from Salary a where empCode=:empCode and month=:month and year=:year", nativeQuery = true)
	public Salary findByEmpCode(@Param("empCode") String empCode, @Param("month") String month,
			@Param("year") Integer year);

	@Query(value = "select distinct(empCode)from Attendance", nativeQuery = true)
	public List<String> getEmployeeList();

	@Query(value = "select at from Attendance at where at.month=:month and at.year=:year order by at.empCode")
	public List<Attendance> getAllEmployee(@Param("month") String month, @Param("year") Integer year);
}
