package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Curriculum;
import com.njit.domain.Project;
import com.njit.domain.User;
import com.njit.service.ProjectService;
import com.opensymphony.xwork2.ActionContext;

@Service
@Transactional
public class ProjectServiceImp extends DaoSupportImp<Project> implements ProjectService{

	@SuppressWarnings("unchecked")
	@Override
	
	public List<Project> findByCourse(Long curriculumId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Project p where p.curriculum.id=? ")
				.setParameter(0, curriculumId).list();
	}



}
