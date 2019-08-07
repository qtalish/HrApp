package com.kgate.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "User")
@Proxy(lazy=false)	
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fname;
	private String mname;
	private String lname;
	private String address;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date dob;
	private String email;
	private Long mob;
	private String designation;
	private String empCode;
	private String bloodGroup;
	private Long aadhar;
	private String pan;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	@Temporal(TemporalType.DATE)
	private Date joiningDate;
	private Integer aLeave;
	private Integer tLeave;
	private Integer bLeave;
	private Long salary;
	private String userType;
	private String password;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public Long getMob() {
		return mob;
	}

	public void setMob(Long mob) {
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

	public Long getAadhar() {
		return aadhar;
	}

	public void setAadhar(Long aadhar) {
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

	public Integer getaLeave() {
		return aLeave;
	}

	public void setaLeave(Integer aLeave) {
		this.aLeave = aLeave;
	}

	public Integer gettLeave() {
		return tLeave;
	}

	public void settLeave(Integer tLeave) {
		this.tLeave = tLeave;
	}

	public Integer getbLeave() {
		return bLeave;
	}

	public void setbLeave(Integer bLeave) {
		this.bLeave = bLeave;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
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
