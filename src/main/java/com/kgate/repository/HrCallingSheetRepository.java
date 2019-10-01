package com.kgate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.HrCallingSheet;

@Repository
public interface HrCallingSheetRepository extends JpaRepository<HrCallingSheet, Integer> {

//	@Query(value = "select * from HrCallingSheet as u where u.updatedDate =:updatedDate and u.empCode=:empCode", nativeQuery = true)
//	List<HrCallingSheet> findByUpdatedDate(@Param("updatedDate") Date updatedDate, @Param("empCode") String empCode);
	List<HrCallingSheet> findByUpdatedDateAndEmpCode(Date date, String empCode);
}
