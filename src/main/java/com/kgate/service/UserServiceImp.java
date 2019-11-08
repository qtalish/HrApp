package com.kgate.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
		DateTime date1 = new DateTime(date);
		DateTimeFormatter formatter = DateTimeFormat.forPattern("MMMMM");
		String month = formatter.print(date1);
		System.out.println(month);
		List<Attendance> listatt = new ArrayList<>();
		List<User> listUser = repo.findEmployee();
		List<Attendance> attDate = attrepo.findAttendanceDate(date);
		System.out.println("ttttttttttttttttttttttttt:::::::::::: " + attDate);
		if (attDate.isEmpty()) {
			for (User user : listUser) {
				Date joinDate = user.getJoiningDate();
				System.out.println("Joining Date : " + joinDate);
				Date calenderDate = date;
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				System.out.println("Calender Date : " + sdf.format(calenderDate));
				if (joinDate.compareTo(calenderDate) <= 0) {
					System.out.println("inside>>>>>>");
					Attendance att = new Attendance();
					att.setFirstName(user.getFname());
					att.setLastName(user.getLname());
					att.setEmpCode(user.getEmpCode());
					att.setAttDate(date);
					att.setStatus("Present");
					att.setMonth(month);
					int year = Calendar.getInstance().get(Calendar.YEAR);
					att.setYear(year);
					attrepo.save(att);
				}
			}
		}
		List<Attendance> listAtt = attrepo.getAttendance(date);
		return listAtt;
	}
	@Override
	public Page<User> findEmployeePage(Pageable pageable) {
		return repo.findEmployeePage(pageable);
	}

	/*
	 * @Override public User findUser(String email, String password, String
	 * userType) { // TODO Auto-generated method stub return repo.findUser(email,
	 * password, userType); }
	 */
	
	@Override
	public User findUser(String email, String password) {
		// TODO Auto-generated method stub
		return repo.findUser(email, password);
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
	public Page<User> searchEmployee(Pageable pageable, String txt) {
		// TODO Auto-generated method stub
		return repo.searchEmployee(pageable, txt);
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
		return null;
	}

}
