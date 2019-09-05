package com.kgate.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

import lombok.Data;

@Entity
@Table(name = "user_salary")
public class Salary {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private Float basicSalary;
	private Float hra;
	private Float conveyanceAllowances;
	private Float medicalAllowances;
	private Float otherAllowances;
	private Float monthlySalary;
	private Float professionalTax;
	private Float totalDeduction;
	private Float additionalDeduction;
	private Float providentFund;
	private Float additionalBonus;
	private String empCode;
	private Float unpaidLeave;
	private Float netSalary;
	private Float totalMonthlySalary;
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

	public Float getTotalMonthlySalary() {
		return totalMonthlySalary;
	}

	public void setTotalMonthlySalary(Float totalMonthlySalary) {
		this.totalMonthlySalary = totalMonthlySalary;
	}

	public Float getNetSalary() {
		return netSalary;
	}

	public void setNetSalary(Float netSalary) {
		this.netSalary = netSalary;
	}

	public Float getUnpaidLeave() {
		return unpaidLeave;
	}

	public void setUnpaidLeave(Float unpaidLeave) {
		this.unpaidLeave = unpaidLeave;
	}

	public Float getProvidentFund() {
		return providentFund;
	}

	public void setProvidentFund(Float providentFund) {
		this.providentFund = providentFund;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Float getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Float basicSalary) {
		this.basicSalary = basicSalary;
	}

	public Float getHra() {
		return hra;
	}

	public void setHra(Float hra) {
		this.hra = hra;
	}

	public Float getConveyanceAllowances() {
		return conveyanceAllowances;
	}

	public void setConveyanceAllowances(Float conveyanceAllowances) {
		this.conveyanceAllowances = conveyanceAllowances;
	}

	public Float getMedicalAllowances() {
		return medicalAllowances;
	}

	public void setMedicalAllowances(Float medicalAllowances) {
		this.medicalAllowances = medicalAllowances;
	}

	public Float getOtherAllowances() {
		return otherAllowances;
	}

	public void setOtherAllowances(Float otherAllowances) {
		this.otherAllowances = otherAllowances;
	}

	public Float getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(Float monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public Float getProfessionalTax() {
		return professionalTax;
	}

	public void setProfessionalTax(Float professionalTax) {
		this.professionalTax = professionalTax;
	}

	public Float getTotalDeduction() {
		return totalDeduction;
	}

	public void setTotalDeduction(Float totalDeduction) {
		this.totalDeduction = totalDeduction;
	}

	public Float getAdditionalDeduction() {
		return additionalDeduction;
	}

	public void setAdditionalDeduction(Float additionalDeduction) {
		this.additionalDeduction = additionalDeduction;
	}

	public Float getAdditionalBonus() {
		return additionalBonus;
	}

	public void setAdditionalBonus(Float additionalBonus) {
		this.additionalBonus = additionalBonus;
	}

	@Override
	public String toString() {
		return "Salary [id=" + id + ", basicSalary=" + basicSalary + ", hra=" + hra + ", conveyanceAllowances="
				+ conveyanceAllowances + ", medicalAllowances=" + medicalAllowances + ", otherAllowances="
				+ otherAllowances + ", monthlySalary=" + monthlySalary + ", professionalTax=" + professionalTax
				+ ", totalDeduction=" + totalDeduction + ", additionalDeduction=" + additionalDeduction
				+ ", providentFund=" + providentFund + ", additionalBonus=" + additionalBonus + ", empCode=" + empCode
				+ ", unpaidLeave=" + unpaidLeave + ", netSalary=" + netSalary + ", totalMonthlySalary="
				+ totalMonthlySalary + ", month=" + month + ", year=" + year + "]";
	}

}
