package com.kgate.entity;



import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HrDailyReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String fname;
	private String lname;
	private Integer totalCalls;
	private String resourcesCalled;
	private String resourcesInterviewed;
	private String taskForTomorrow;
	private String interviewPlannedForTomorrow;
	private String impediments;
	private String empCode;
	private Date date;
	private String month;
	private Integer year;
	
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

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

	public Integer getTotalCalls() {
		return totalCalls;
	}

	public void setTotalCalls(Integer totalCalls) {
		this.totalCalls = totalCalls;
	}

	public String getResourcesCalled() {
		return resourcesCalled;
	}

	public void setResourcesCalled(String resourcesCalled) {
		this.resourcesCalled = resourcesCalled;
	}

	public String getResourcesInterviewed() {
		return resourcesInterviewed;
	}

	public void setResourcesInterviewed(String resourcesInterviewed) {
		this.resourcesInterviewed = resourcesInterviewed;
	}

	public String getTaskForTomorrow() {
		return taskForTomorrow;
	}

	public void setTaskForTomorrow(String taskForTomorrow) {
		this.taskForTomorrow = taskForTomorrow;
	}

	public String getInterviewPlannedForTomorrow() {
		return interviewPlannedForTomorrow;
	}

	public void setInterviewPlannedForTomorrow(String interviewPlannedForTomorrow) {
		this.interviewPlannedForTomorrow = interviewPlannedForTomorrow;
	}

	public String getImpediments() {
		return impediments;
	}

	public void setImpediments(String impediments) {
		this.impediments = impediments;
	}


	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	@Override
	public String toString() {
		return "HrDailyReport [id=" + id + ", fname=" + fname + ", lname=" + lname + ", totalCalls=" + totalCalls
				+ ", resourcesCalled=" + resourcesCalled + ", resourcesInterviewed=" + resourcesInterviewed
				+ ", taskForTomorrow=" + taskForTomorrow + ", interviewPlannedForTomorrow="
				+ interviewPlannedForTomorrow + ", impediments=" + impediments + ", empCode=" + empCode + ", date="
				+ date + ", month=" + month + ", year=" + year + "]";
	}

	
	

	
}
