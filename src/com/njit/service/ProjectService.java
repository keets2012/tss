package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Project;

public interface ProjectService extends DaoSupport<Project>{

	List<Project> findByCourse(Long curriculumId);







}
