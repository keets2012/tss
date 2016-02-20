package com.njit.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 部门
 * 
 * @author rongxin
 * 
 */
public class Department implements Serializable{
	private Long id;
	private Set<User> users = new HashSet<User>();
	private Department parent;
	private Set<Department> children = new HashSet<Department>();

	private String name;
	private String description;
	private Set<Course> courses = new HashSet<Course>();
	private Set<Experiment> exps = new HashSet<Experiment>();
	private Set<Task> tasks = new HashSet<Task>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Set<Department> getChildren() {
		return children;
	}

	public void setChildren(Set<Department> children) {
		this.children = children;
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

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}

	public Set<Experiment> getExps() {
		return exps;
	}

	public void setExps(Set<Experiment> exps) {
		this.exps = exps;
	}

	public Set<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Set<Task> tasks) {
		this.tasks = tasks;
	}

 
	
	

}
