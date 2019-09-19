package com.kgate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class UserLeaves {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer workingDays;
	private Float totalDaysWorked;
	private Float totalLeaves;
	private Float paidLeaves;
	private Float balanceLeaves;
	private String empCode;
	private String month;
	private Float unpaidLeaves;
	
	public Float getUnpaidLeaves() {
		return unpaidLeaves;
	}

	public void setUnpaidLeaves(Float unpaidLeaves) {
		this.unpaidLeaves = unpaidLeaves;
	}

	private Integer year;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Integer workingDays) {
		this.workingDays = workingDays;
	}

	public Float getTotalDaysWorked() {
		return totalDaysWorked;
	}

	public void setTotalDaysWorked(Float totalDaysWorked) {
		this.totalDaysWorked = totalDaysWorked;
	}

	public Float getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(Float totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	public Float getPaidLeaves() {
		return paidLeaves;
	}

	public void setPaidLeaves(Float paidLeaves) {
		this.paidLeaves = paidLeaves;
	}

	public Float getBalanceLeaves() {
		return balanceLeaves;
	}

	public void setBalanceLeaves(Float balanceLeaves) {
		this.balanceLeaves = balanceLeaves;
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

	@Override
	public String toString() {
		return "UserLeaves [id=" + id + ", workingDays=" + workingDays + ", totalDaysWorked=" + totalDaysWorked
				+ ", totalLeaves=" + totalLeaves + ", paidLeaves=" + paidLeaves + ", balanceLeaves=" + balanceLeaves
				+ ", empCode=" + empCode + ", month=" + month + ", year=" + year + "]";
	}

}
