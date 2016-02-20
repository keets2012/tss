package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author rongxin
 *
 */
public class Curriculum implements Serializable{

	private Long id;
	private String courseNo;
	private String name;
	private String description;
	private float credit;
	private Set<Project> projects = new HashSet<Project>();//实验项目
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getCourseNo() {
		return courseNo;
	}
	public void setCourseNo(String courseNo) {
		this.courseNo = courseNo;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public Set<Project> getProjects() {
		return projects;
	}
	public void setProjects(Set<Project> projects) {
		this.projects = projects;
	}
	
	
}
