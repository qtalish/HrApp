package com.kgate.entity;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.lang.Nullable;

@Entity
@Table
public class UserDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Integer id;

	@Column(name = "dname")
	@Nullable
	private String dname;

	@Column(name = "description")
	@Nullable
	private String description;

	@Column(name = "document")
	@Lob
	@Basic(fetch = FetchType.EAGER)
	private byte[] document;

	@Column(name = "document_type")
	@Nullable
	private String documentType;

	@Column(name = "created")
	@Nullable
	private Date created;

	private String empCode;
	
	private String userType;

	public Integer getId() {
		return id;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public String getDocumentType() {
		return documentType;
	}

	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getEmpCode() {
		return empCode;
	}

	public void setEmpCode(String empCode) {
		this.empCode = empCode;
	}

	@Override
	public String toString() {
		return "UserDocument [id=" + id + ", dname=" + dname + ", description=" + description + ", document="
				+ Arrays.toString(document) + ", documentType=" + documentType + ", created=" + created + ", empCode="
				+ empCode + ", userType=" + userType + "]";
	}
}
