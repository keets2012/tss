package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Course;
import com.njit.domain.Share;
import com.njit.domain.User;
import com.njit.service.CourseService;
import com.njit.service.ShareService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class ShareServiceImp extends DaoSupportImp<Share> implements ShareService{

	@Override
	public List<Share> findByUser() {
		// TODO Auto-generated method stub
		
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery("from Share s where s.user.id=?  ").setParameter(0, user.getId()).list();
	}

	


}
