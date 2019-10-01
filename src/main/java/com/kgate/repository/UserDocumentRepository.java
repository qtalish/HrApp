package com.kgate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.UserDocument;

@Repository
@Transactional
public interface UserDocumentRepository extends JpaRepository<UserDocument, Integer> {

	@Query("select u from UserDocument u where u.id=:docCode")
	UserDocument download(@Param("docCode") int docCode);

	@Query("select u from UserDocument u where u.empCode=:empCode")
	List<UserDocument> findDoc(@Param("empCode") String empCode);
	
	List<UserDocument> findByEmpCode(String empcode);


}
