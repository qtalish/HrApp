package com.kgate.service;

import java.text.SimpleDateFormat;
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
		String ddd[] = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
				"October", "November", "December" };
		List<Attendance> listatt = new ArrayList<>();
		List<User> listUser = repo.findEmployee();

		List<Attendance> attDate = attrepo.findAttendanceDate(date);

		for (User user : listUser) {
			Date joinDate = user.getJoiningDate();
			System.out.println("Joining Date : "+joinDate);

			Date calenderDate = date;

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			System.out.println("Calender Date : "+sdf.format(calenderDate));

			if (joinDate.compareTo(calenderDate) < 0) {

			}

			else if (joinDate.compareTo(calenderDate) > 0) {
				System.out.println("joiningDate is AFTER calenderDate" + joinDate.compareTo(calenderDate));
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
	public List<User> searchEmployee(String txt) {
		// TODO Auto-generated method stub
		return repo.searchEmployee(txt);
	}

	@Override
	public List<User> searchEmployee() {
		// TODO Auto-generated method stub
		return repo.findEmployee();
	}

}
