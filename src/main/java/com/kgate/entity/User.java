package com.kgate.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table(name = "User")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String fname;
	private String mname;
	private String lname;
	private String address;
	private Date dob;
	private String email;
	@Nullable
	private long mob;
	private String designation;
	private String empCode;
	private String bloodGroup;
	@Nullable
	private long aadhar;
	private String pan;
	private Date joiningDate;
	@Nullable
	private int aLeave;
	@Nullable
	private int tLeave;
	@Nullable
	private int bLeave;
	@Nullable
	private long salary;
	private String userType;
	private String password;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getMob() {
		return mob;
	}

	public void setMob(long mob) {
		this.mob = mob;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public long getAadhar() {
		return aadhar;
	}

	public void setAadhar(long aadhar) {
		this.aadhar = aadhar;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
	}

	public int getaLeave() {
		return aLeave;
	}

	public void setaLeave(int aLeave) {
		this.aLeave = aLeave;
	}

	public int gettLeave() {
		return tLeave;
	}

	public void settLeave(int tLeave) {
		this.tLeave = tLeave;
	}

	public int getbLeave() {
		return bLeave;
	}

	public void setbLeave(int bLeave) {
		this.bLeave = bLeave;
	}

	public long getSalary() {
		return salary;
	}

	public void setSalary(long salary) {
		this.salary = salary;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fname=" + fname + ", mname=" + mname + ", lname=" + lname + ", address=" + address
				+ ", dob=" + dob + ", email=" + email + ", mob=" + mob + ", designation=" + designation + ", empCode="
				+ empCode + ", bloodGroup=" + bloodGroup + ", aadhar=" + aadhar + ", pan=" + pan + ", joiningDate="
				+ joiningDate + ", aLeave=" + aLeave + ", tLeave=" + tLeave + ", bLeave=" + bLeave + ", salary="
				+ salary + ", userType=" + userType + ", password=" + password + "]";
	}

}
