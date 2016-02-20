package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Department;
import com.njit.domain.Report;
import com.njit.domain.Task;

public interface TaskService extends DaoSupport<Task>{

	List<Task> findByUser();

	List<Task> findByStudent();

	List<Task> findUnhandle(List<Report> reportList, List<Task> allTaskList);

	List<Task> findByCourse(Long courseId);

	Task getByName(String taskName, List<Task> taskList);

//	List<Department> findClassByTask(Long taskId);




}
