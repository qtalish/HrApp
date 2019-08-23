package com.kgate.service;

import java.util.Date;
import java.util.List;

import com.kgate.entity.Attendance;
import com.kgate.entity.User;

public interface UserService {

	public List<Attendance> getAttendance(Date date);

	public List<User> findEmployee();

	public User findUser(String email, String password, String userType);

	public User save(User user);

	public List<User> findAll();

	public User fetchPassword(String email);

}
