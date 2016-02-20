package com.njit.service.imp;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Privilege;
import com.njit.service.PrivilegeService;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class PrivilegeServiceImp extends DaoSupportImp<Privilege> implements PrivilegeService{

	
	@Override
	public List<Privilege> findTopList() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Privilege p where p.parent is null").list();
	}


	public Collection<String> getAllPrivilegeUrls() {
		return getSession().createQuery(//
				"SELECT DISTINCT p.url FROM Privilege p WHERE p.url IS NOT NULL")//
				.list();
}

	
}
