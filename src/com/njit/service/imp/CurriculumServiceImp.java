package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.User;
import com.njit.service.CourseService;
import com.njit.service.CurriculumService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class CurriculumServiceImp extends DaoSupportImp<Curriculum> implements CurriculumService{

	@Override
	public Curriculum getByName(String name) {
		// TODO Auto-generated method stub
		return (Curriculum) getSession().createQuery("from Curriculum c where c.name=?  ").setParameter(0, name).uniqueResult();
	}

	@Override
	public Curriculum findByNo(String courseNo) {
		// TODO Auto-generated method stub
		return (Curriculum) getSession().createQuery("from Curriculum c where c.courseNo=?  ").setParameter(0, courseNo).uniqueResult();
	}


}
