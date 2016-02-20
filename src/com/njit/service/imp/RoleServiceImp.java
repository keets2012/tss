package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.dao.RoleDao;
import com.njit.domain.Role;
import com.njit.service.RoleService;

@Service
@Transactional
public class RoleServiceImp extends DaoSupportImp<Role> implements RoleService{

//	@Resource
//	private RoleDao roleDao;
//	
//	
//	@Override
//	public List<Role> findAll() {
//		// TODO Auto-generated method stub
//		return roleDao.findAll();
//	}
//
//	@Override
//	public void delete(Long id) {
//		// TODO Auto-generated method stub
//		roleDao.delete(id);
//	}
//
//	@Override
//	public void save(Role role) {
//		// TODO Auto-generated method stub
//		roleDao.save(role);
//	}
//
//	@Override
//	public Role getById(Long id) {
//		// TODO Auto-generated method stub
//		return roleDao.getById(id);
//	}
//
//	@Override
//	public void update(Role role) {
//		// TODO Auto-generated method stub
//		roleDao.update(role);
//	}



}
