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

	@Query("select u from User u where u.email=:email and u.password=:password")
	User findUser(@Param("email") String email, @Param("password") String password);

	@Query("select u from User u where u.email=:email")
	User fetchPassword(@Param("email") String email);

	@Query("select u from User u where u.fname=:fname")
	List<User> searchEmployee(@Param("fname") String fname);

	@Query(value = "SELECT * FROM User u WHERE CONCAT(u.fname,u.designation,u.address,u.lname,u.aadhar,u.email) LIKE %:txt%", nativeQuery = true)
	Page<User> searchEmployee(Pageable pageable, @Param("txt") String txt);


	@Query(value = "select * from User as u where u.usertype !='ADMIN'", nativeQuery = true)
	Page<User> findEmployeePage(Pageable pageable);


	@Query(value = "select * from User as u where u.usertype !='ADMIN'", nativeQuery = true)
	List<User> findEmployee();

	User findByEmail(String email);
	
	User findByEmpCode(String empCode);


}
