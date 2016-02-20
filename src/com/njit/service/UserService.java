package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Course;
import com.njit.domain.User;

public interface UserService extends DaoSupport<User>{

	User findByLoginNameAndPassword(String loginName, String password);

	List<User> findAllTeachers();

	int queryTotal(Long departmentId);
//查找未提交学生
	List<User> findClassStudent(Long departmentId, List<User> subList);

	List<Course> findMyCourse();
	
	User findByLoginName(String loginName);




}
