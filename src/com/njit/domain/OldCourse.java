package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author rongxin
 *
 */
public class OldCourse implements Serializable{

	private Long id;
	private String courseNo;
	private String name;
	private String description;
	private float credit;
	private String term;
	private String deptNames;//上课班级
	private String userName;//任课教师
	
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

	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getDeptNames() {
		return deptNames;
	}
	public void setDeptNames(String deptNames) {
		this.deptNames = deptNames;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	
	
}
