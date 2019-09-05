package com.kgate.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kgate.entity.Leave;
import com.kgate.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.email=:email and u.password=:password and u.userType=:userType")
	User findUser(@Param("email") String email, @Param("password") String password, @Param("userType") String userType);

	@Query("select u from User u where u.email=:email")
	User fetchPassword(@Param("email") String email);
	
	@Query("select u from User u where u.fname=:fname")
	List<User> searchEmployee(@Param("fname") String fname);

	@Query(value = "select * from user as u where u.usertype='Employee'", nativeQuery = true)
	Page<User> findEmployeePage(Pageable pageable);
	
	@Query(value = "select * from user as u where u.usertype='Employee'", nativeQuery = true)
	List<User> findEmployee();

	public String findByEmail(String email);

}
