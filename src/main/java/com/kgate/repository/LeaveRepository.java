package com.kgate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {
	
	
 public List<Leave> findByEmpCode(String empCode);
 
 
    @Modifying
	@Query(value = "update emp_leave a set a.status=:status where a.id=:id", nativeQuery = true)
	void updateStatus(@Param("id") Integer id, @Param("status") String status);

}
