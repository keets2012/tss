package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author rongxin
 *
 */


public class Experiment implements Serializable{

	private Long id;
	private String expId;
	private String expTime;
	private String description;
	private String expTerm;	
	private Project project;
	private Department dept;
	private Laboratory lab;
	private User user;
		
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Department getDept() {
		return dept;
	}
	public void setDept(Department dept) {
		this.dept = dept;
	}
	public Laboratory getLab() {
		return lab;
	}
	public void setLab(Laboratory lab) {
		this.lab = lab;
	}
	public String getExpTime() {
		return expTime;
	}
	public void setExpTime(String expTime) {
		this.expTime = expTime;
	}
	public Project getProject() {
		return project;
	}
	public void setProject(Project project) {
		this.project = project;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getExpTerm() {
		return expTerm;
	}
	public void setExpTerm(String expTerm) {
		this.expTerm = expTerm;
	}

	
}
