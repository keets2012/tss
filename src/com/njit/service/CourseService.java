package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Experiment;

public interface CourseService extends DaoSupport<Course>{
	List<Course> findByUser();

	List<Department> findByCourse(Long courseId);

	List<String> findAllTerm();

	void deleteAll(List<Course> courseList, List<Experiment> expList);

	Course getByName(String courseName, List<Course> courseList);

}
