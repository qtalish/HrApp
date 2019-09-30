package com.kgate.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class AttendanceImport {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String empCode;
	
	private String firstName;
	
	private String lastName;
	
	private String month;
	
	private Integer year;
	
	private String d1;
	private String d2;
	private String d3;
	private String d4;
	private String d5;
	private String d6;
	private String d7;
	private String d8;
	private String d9;
	private String d10;
	private String d11;
	private String d12;
	
	private String d13;
	private String d14;
	private String d15;
	private String d16;
	private String d17;
	private String d18;
	
	private String d19;
	private String d20;
	private String d21;
	private String d22;
	private String d23;
	private String d24;
	private String d25;
	private String d26;
	private String d27;
	private String d28;
	private String d29;
	private String d30;
	private String d31;
}
