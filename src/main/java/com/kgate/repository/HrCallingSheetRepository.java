package com.kgate.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.HrCallingSheet;
import com.kgate.entity.User;
import com.kgate.entity.UserDocument;

@Repository
public interface HrCallingSheetRepository extends JpaRepository<HrCallingSheet, Integer> {

//	@Query(value = "select * from HrCallingSheet as u where u.updatedDate =:updatedDate and u.empCode=:empCode", nativeQuery = true)
//	List<HrCallingSheet> findByUpdatedDate(@Param("updatedDate") Date updatedDate, @Param("empCode") String empCode);
//	List<HrCallingSheet> findByUpdatedDateAndEmpCode(Date date, String empCode);
	
	List<HrCallingSheet> findByEmpCode(String empcode);
	
	@Query(value="SELECT * from HrCallingSheet where empCode=:empCode", nativeQuery=true)
	Page<HrCallingSheet> findCandidatePage(Pageable pageable,@Param("empCode")String empCode);
	
	
	/*@Query(value = "SELECT * FROM hrcallingsheet u WHERE CONCAT(u.candidateName,u.skills,u.totalExp,u.mob,u.email,u.status) LIKE %:txt%", nativeQuery = true)
	Page<HrCallingSheet> searchCandidate(Pageable pageable, @Param("txt") String txt);*/
	
	@Query(value = "SELECT * FROM HrCallingSheet hr WHERE CONCAT(hr.candidateName,hr.clientName,hr.relevantExp,hr.mob,hr.email,hr.status) LIKE %:txt%", nativeQuery = true)
	Page<HrCallingSheet> searchCandidate(Pageable pageable, @Param("txt") String txt);
	
	
	@Modifying
	@Query(value = "update HrCallingSheet a set a.status=:status where a.id=:id", nativeQuery = true)
	void updateStatus(@Param("id") Integer id, @Param("status") String status);
	
}
