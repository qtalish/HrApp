package com.kgate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kgate.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {

}
