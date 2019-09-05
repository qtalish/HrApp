package com.kgate.service;

import com.kgate.entity.UserLeaves;

public interface LeavesService {

	public UserLeaves getLeavesDetails(String empCode, String month, Integer year);

	public UserLeaves getPreviousLeaves(String empc,String monthLeaves,Integer year);
}
