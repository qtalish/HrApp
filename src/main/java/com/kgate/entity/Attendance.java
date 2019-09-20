package com.kgate.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Data
@Entity
@Table
public class Attendance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer id;
	public String firstName;
	public String lastName;
	public String status;
	public String remarks;
	public Date attDate;
	public String empCode;
	private String month;
	private Integer year;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getAttDate() {
		return attDate;
	}

	public void setAttDate(Date attDate) {
		this.attDate = attDate;
	}

	@Override
	public String toString() {
		return "Attendance [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", status=" + status
				+ ", remarks=" + remarks + ", attDate=" + attDate + ", empCode=" + empCode + ", month=" + month
				+ ", year=" + year + "]";
	}

}
