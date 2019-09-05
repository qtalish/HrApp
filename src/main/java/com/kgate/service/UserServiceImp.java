package com.kgate.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.kgate.entity.Attendance;
import com.kgate.entity.Leave;
import com.kgate.entity.User;
import com.kgate.repository.AttendanceRepository;
import com.kgate.repository.LeaveRepository;
import com.kgate.repository.UserRepository;

@Service
@Transactional
public class UserServiceImp implements UserService {
	@Autowired
	UserRepository repo;
	@Autowired
	AttendanceRepository attrepo;
	@Autowired
	LeaveRepository lrepo;
	
	@Override
	public List<Attendance> getAttendance(Date date) {
		// TODO Auto-generated method stub
		String ddd [] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		List<Attendance> listatt = new ArrayList<>();
		List<User> listUser = repo.findEmployee();
		List<Attendance> attDate = attrepo.findAttendanceDate(date);
		System.out.println("ttttttttttttttttttttttttt:::::::::::: " + attDate);
		if (attDate.isEmpty()) {
			for (int i = 0; i < listUser.size(); i++) {
				System.out.println("inside>>>>>>");
				Attendance att = new Attendance();
				att.setFirstName(listUser.get(i).getFname());
				att.setLastName(listUser.get(i).getLname());
				att.setEmpCode(listUser.get(i).getEmpCode());
				att.setAttDate(date);
				Date d = new Date();
				System.out.println("The current month is " + ddd[d.getMonth()]);
				att.setMonth(ddd[d.getMonth()]);
				int year = Calendar.getInstance().get(Calendar.YEAR);
				att.setYear(year);
				attrepo.save(att);
//			listatt.add(att);

			}
		}
		List<Attendance> listAtt = attrepo.getAttendance(date);
		return listAtt;
	}

	@Override
	public Page<User> findEmployeePage(Pageable pageable) {
		return repo.findEmployeePage(pageable);
	}

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
	public User fetchPassword(String email) {
		// TODO Auto-generated method stub
		return repo.fetchPassword(email);
	}

	@Override
	public List<User> findEmployee() {
		return repo.findEmployee();
	}

	@Override
	public Leave save(Leave leave) {
		// TODO Auto-generated method stub
		return lrepo.save(leave);
	}

	@Override
	public List<User> searchEmployee(String txt) {
		// TODO Auto-generated method stub
		return repo.searchEmployee(txt);
	}

	@Override
	public List<User> searchEmployee() {
		// TODO Auto-generated method stub
		return repo.findEmployee();
	}

	@Override
	public String findByEmail(String status) {
		// TODO Auto-generated method stub
		return repo.findByEmail(status);
	}
}
