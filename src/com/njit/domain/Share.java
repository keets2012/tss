package com.njit.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * @author rongxin
 *
 */
public class Share implements Serializable{

	private Long id;
	private String name;
	private String description;
	private String filePath;
	private User user;
	private Course course;
	
	private Date uploadTime;// 上传时间
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	


	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
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
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}


	
	
}
