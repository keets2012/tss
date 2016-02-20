package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * @author rongxin
 *
 */


public class Project implements Serializable{

	private Long id;
	private String name;
	private String projectNo;
	private String description;
	private int total;
	private Curriculum curriculum;
	
	
	
	public String getProjectNo() {
		return projectNo;
	}
	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
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
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}

	
}
