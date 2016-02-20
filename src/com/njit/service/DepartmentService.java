package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Department;

public interface DepartmentService extends DaoSupport<Department>{

//	List<Department> findAll();
//
//	void delete(Long id);
//
//	void save(Department department);
//
//	Department getById(Long id);
//
//	void update(Department department);

	List<Department> findTopList();

	List<Department> findChildren(Long parentId);

	List<Department> findAllClass();

	List<Department> findAllMyClass();

	Department getByName(String dept, List<Department> departmentList);

	List<Department> getByNames(String dept, List<Department> departmentList);

	List<Department> findByMyCourse(Long courseId);



}
