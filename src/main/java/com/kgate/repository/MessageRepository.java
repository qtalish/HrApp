package com.kgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kgate.entity.MessageEmployee;

public interface MessageRepository extends JpaRepository<MessageEmployee, Integer>{

}
