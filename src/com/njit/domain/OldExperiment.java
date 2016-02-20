package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author rongxin
 *
 */


public class OldExperiment implements Serializable{

	private Long id;
	private String expId;
	private String expTime;
	private String description;
	private String expTerm;
	private String projectName;
	private String courseName;
	private String deptName;
	private String labName;
	private String userName;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	
	public String getExpId() {
		return expId;
	}
	public void setExpId(String expId) {
		this.expId = expId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getExpTerm() {
		return expTerm;
	}
	public void setExpTerm(String expTerm) {
		this.expTerm = expTerm;
	}

	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getLabName() {
		return labName;
	}
	public void setLabName(String labName) {
		this.labName = labName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
}
