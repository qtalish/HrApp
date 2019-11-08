package com.kgate.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.UserLeaves;

@Repository
@Transactional
public interface LeavesRepository extends JpaRepository<UserLeaves, Integer> {

	@Query(value="select ul from UserLeaves ul where ul.empCode=:empCode and ul.month=:month and ul.year=:year")
	UserLeaves getLeavesDetails(@Param("empCode")String empCode,@Param("month") String month,@Param("year") Integer year);
   
	@Query(value = "select ul from UserLeaves ul where ul.empCode=:empc and ul.month=:monthLeaves and ul.year=:year")
	UserLeaves getPreviousLeaves(@Param("empc")String empc,@Param("monthLeaves")String monthLeaves,@Param("year")Integer year);
	
	@Query(value = "select * from userleaves where empCode=:empCode and month=:month and year=:year", nativeQuery = true)
    UserLeaves getBalanceLeave(@Param("empCode") String empCode, @Param("month") String month, @Param("year") int year);
	
}
