package com.kgate.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kgate.entity.Attendence;
import com.kgate.entity.User;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository repo;
	
	@Autowired
	AttendanceRepository attrepo;

	

	@Override
	public User findUser(String email, String password, String userType) {
		// TODO Auto-generated method stub
		return repo.findUser(email, password, userType);
	}

	@Override
	public User save(User user) {
		// TODO Auto-generated method stub
		return repo.save(user);
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public User fetchPassword(String email) {
		// TODO Auto-generated method stub
		return repo.fetchPassword(email);
	}

	@Override
	public List<Attendence> getAttendance(Date date) {
		// TODO Auto-generated method stub
		List<Attendence> listatt = new ArrayList<>();
		List<User> listUser = repo.findEmployee();
		for (int i = 0; i < listUser.size(); i++) {
			Attendence att = new Attendence();
			att.setFirstName(listUser.get(i).getFname());
			att.setLastName(listUser.get(i).getLname());
			att.setAttDate(date);
			attrepo.save(att);
//			listatt.add(att);
		}
		List<Attendence> listAtt = attrepo.getAttendance();
		return listAtt;
	}

	@Override
	public List<User> findEmployee() {
		// TODO Auto-generated method stub
		return repo.findEmployee();
	}
	
	


}
