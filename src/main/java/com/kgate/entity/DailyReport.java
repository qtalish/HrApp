package com.kgate.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name="DailyReport")
public class DailyReport 
{
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Integer id;
  private String firstName;
  private String lastName;
  private String taskDoneToday;
  private String taskForTomorrow;
  private String impediments;
  private String empCode; 
  private Date date;
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
public String getTaskDoneToday() {
	return taskDoneToday;
}
public void setTaskDoneToday(String taskDoneToday) {
	this.taskDoneToday = taskDoneToday;
}
public String getTaskForTomorrow() {
	return taskForTomorrow;
}
public void setTaskForTomorrow(String taskForTomorrow) {
	this.taskForTomorrow = taskForTomorrow;
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
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
@Override
public String toString() {
	return "DailyReport [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", taskDoneToday="
			+ taskDoneToday + ", taskForTomorrow=" + taskForTomorrow + ", impediments=" + impediments + ", empCode="
			+ empCode + ", date=" + date + ", month=" + month + ", year=" + year + "]";
}


  
  

}
