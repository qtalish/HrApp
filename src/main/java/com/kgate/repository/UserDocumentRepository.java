package com.kgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.UserDocument;

@Repository
@Transactional
public interface UserDocumentRepository extends JpaRepository<UserDocument,Integer > {

}
