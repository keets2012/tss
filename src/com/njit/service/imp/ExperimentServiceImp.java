package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Experiment;
import com.njit.domain.User;
import com.njit.service.ExperimentService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class ExperimentServiceImp extends DaoSupportImp<Experiment> implements ExperimentService{

	
	@Override
	public List<Experiment> findByUser() {
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery(" from Experiment e where e.user.id=? ")				
				.setParameter(0, user.getId()).list();
	}

	@Override
	public List<Experiment> findByStudent() {
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery(" from Experiment e where e.dept.id=? ")				
				.setParameter(0, user.getDepartment().getId()).list();
	}




}
