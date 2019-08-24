package com.kgate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaves")
public class UserLeaves {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Integer workingDays;
	private Integer totalDaysWorked;
	private Integer totalLeaves;
	private Integer paidLeaves;
	private Integer balanceLeaves;

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

	public Integer getTotalDaysWorked() {
		return totalDaysWorked;
	}

	public void setTotalDaysWorked(Integer totalDaysWorked) {
		this.totalDaysWorked = totalDaysWorked;
	}

	public Integer getTotalLeaves() {
		return totalLeaves;
	}

	public void setTotalLeaves(Integer totalLeaves) {
		this.totalLeaves = totalLeaves;
	}

	public Integer getPaidLeaves() {
		return paidLeaves;
	}

	public void setPaidLeaves(Integer paidLeaves) {
		this.paidLeaves = paidLeaves;
	}

	public Integer getBalanceLeaves() {
		return balanceLeaves;
	}

	public void setBalanceLeaves(Integer balanceLeaves) {
		this.balanceLeaves = balanceLeaves;
	}

	@Override
	public String toString() {
		return "UserLeaves [id=" + id + ", workingDays=" + workingDays + ", totalDaysWorked=" + totalDaysWorked
				+ ", totalLeaves=" + totalLeaves + ", paidLeaves=" + paidLeaves + ", balanceLeaves=" + balanceLeaves
				+ "]";
	}

}
