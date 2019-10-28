package com.kgate.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class HrCallingSheet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Date date;
	private String candidateName;
	private String skills;
	private String clientName;
	private Long mob;
	private String email;
	private String totalExp;
	private String relevantExp;
	private String currentCTC;
	private String expectedCTC;
	private String noticePeriod;
	private String organization;
	private String currentLocation;
	private String prefferedLocation;
	private String anyOffer;
	private Float testScore;
	private String status;
	private String source;
	private String remarks;
	private Date updateDate;
	private Date interviewDate;
	private String fname;
	private String lname;
	private String empCode;
	private String month;
	private Integer year;
	

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public Long getMob() {
		return mob;
	}

	public void setMob(Long mob) {
		this.mob = mob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTotalExp() {
		return totalExp;
	}

	public void setTotalExp(String totalExp) {
		this.totalExp = totalExp;
	}

	public String getRelevantExp() {
		return relevantExp;
	}

	public void setRelevantExp(String relevantExp) {
		this.relevantExp = relevantExp;
	}

	public String getCurrentCTC() {
		return currentCTC;
	}

	public void setCurrentCTC(String currentCTC) {
		this.currentCTC = currentCTC;
	}

	public String getExpectedCTC() {
		return expectedCTC;
	}

	public void setExpectedCTC(String expectedCTC) {
		this.expectedCTC = expectedCTC;
	}

	public String getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(String noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(String currentLocation) {
		this.currentLocation = currentLocation;
	}

	public String getPrefferedLocation() {
		return prefferedLocation;
	}

	public void setPrefferedLocation(String prefferedLocation) {
		this.prefferedLocation = prefferedLocation;
	}

	public String getAnyOffer() {
		return anyOffer;
	}

	public void setAnyOffer(String anyOffer) {
		this.anyOffer = anyOffer;
	}

	public Float getTestScore() {
		return testScore;
	}

	public void setTestScore(Float testScore) {
		this.testScore = testScore;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	@Override
	public String toString() {
		return "HrCallingSheet [id=" + id + ", date=" + date + ", candidateName=" + candidateName + ", skills=" + skills
				+ ", clientName=" + clientName + ", mob=" + mob + ", email=" + email + ", totalExp=" + totalExp
				+ ", relevantExp=" + relevantExp + ", currentCTC=" + currentCTC + ", expectedCTC=" + expectedCTC
				+ ", noticePeriod=" + noticePeriod + ", organization=" + organization + ", currentLocation="
				+ currentLocation + ", prefferedLocation=" + prefferedLocation + ", anyOffer=" + anyOffer
				+ ", testScore=" + testScore + ", status=" + status + ", source=" + source + ", remarks=" + remarks
				+ ", updateDate=" + updateDate + ", interviewDate=" + interviewDate + ", fname=" + fname + ", lname="
				+ lname + ", empCode=" + empCode + ", month=" + month + ", year=" + year + ", getFname()=" + getFname()
				+ ", getLname()=" + getLname() + ", getEmpCode()=" + getEmpCode() + ", getMonth()=" + getMonth()
				+ ", getYear()=" + getYear() + ", getId()=" + getId() + ", getDate()=" + getDate()
				+ ", getCandidateName()=" + getCandidateName() + ", getSkills()=" + getSkills() + ", getClientName()="
				+ getClientName() + ", getMob()=" + getMob() + ", getEmail()=" + getEmail() + ", getTotalExp()="
				+ getTotalExp() + ", getRelevantExp()=" + getRelevantExp() + ", getCurrentCTC()=" + getCurrentCTC()
				+ ", getExpectedCTC()=" + getExpectedCTC() + ", getNoticePeriod()=" + getNoticePeriod()
				+ ", getOrganization()=" + getOrganization() + ", getCurrentLocation()=" + getCurrentLocation()
				+ ", getPrefferedLocation()=" + getPrefferedLocation() + ", getAnyOffer()=" + getAnyOffer()
				+ ", getTestScore()=" + getTestScore() + ", getStatus()=" + getStatus() + ", getSource()=" + getSource()
				+ ", getRemarks()=" + getRemarks() + ", getUpdateDate()=" + getUpdateDate() + ", getInterviewDate()="
				+ getInterviewDate() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	

}
