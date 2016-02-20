package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author rongxin
 *
 */
public class Course implements Serializable{

	private Long id;
	private String courseNo;
	private String name;
	private String description;
	private float credit;
	private String term;
	private Set<Department> depts = new HashSet<Department>();//上课班级
	private User user;//任课教师
	private Curriculum curriculum;
	private Set<Share> shares = new HashSet<Share>(); //共享的文件
	private Set<Task> tasks = new HashSet<Task>(); //共享的文件
	
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
	public Set<Department> getDepts() {
		return depts;
	}
	public void setDepts(Set<Department> depts) {
		this.depts = depts;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
	public Set<Share> getShares() {
		return shares;
	}
	public void setShares(Set<Share> shares) {
		this.shares = shares;
	}
	public Set<Task> getTasks() {
		return tasks;
	}
	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}
	public Curriculum getCurriculum() {
		return curriculum;
	}
	public void setCurriculum(Curriculum curriculum) {
		this.curriculum = curriculum;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}

	
	
}
