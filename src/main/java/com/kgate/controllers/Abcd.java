
package com.kgate.controllers;

import java.util.Set;

public class Abcd {

	private Integer id;
	private Double presentCount;
	private Double absentCount;
	private Double halfDayCount;
	private Double holidayCount;
	private Double weekoffCount;
	private Set<String> monthName;

	public Set<String> getMonthName() {
		return monthName;
	}

	public void setMonthName(Set<String> month) {
		this.monthName = month;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPresentCount() {
		return presentCount;
	}

	public void setPresentCount(Double presentCount) {
		this.presentCount = presentCount;
	}

	public Double getAbsentCount() {
		return absentCount;
	}

	public void setAbsentCount(Double absentCount) {
		this.absentCount = absentCount;
	}

	public Double getHalfDayCount() {
		return halfDayCount;
	}

	public void setHalfDayCount(Double halfDayCount) {
		this.halfDayCount = halfDayCount;
	}

	public Double getHolidayCount() {
		return holidayCount;
	}

	public void setHolidayCount(Double holidayCount) {
		this.holidayCount = holidayCount;
	}

	public Double getWeekoffCount() {
		return weekoffCount;
	}

	public void setWeekoffCount(Double weekoffCount) {
		this.weekoffCount = weekoffCount;
	}

	@Override
	public String toString() {
		return "Abcd [id=" + id + ", presentCount=" + presentCount + ", absentCount=" + absentCount + ", halfDayCount="
				+ halfDayCount + ", holidayCount=" + holidayCount + ", weekoffCount=" + weekoffCount + ", monthName="
				+ monthName + "]";
	}

}
