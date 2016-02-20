package com.njit.service.imp;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.dao.DepartmentDao;
import com.njit.domain.Department;
import com.njit.domain.User;
import com.njit.service.DepartmentService;
import com.opensymphony.xwork2.ActionContext;


@Service
@Transactional
@SuppressWarnings("unchecked")
public class DepartmentServiceImp extends DaoSupportImp<Department> implements DepartmentService{

	@Resource
	private SessionFactory sf;

	@Override
	public List<Department> findTopList() {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().createQuery
				("from Department d where d.parent is null ").list();
	}

	@Override
	public List<Department> findChildren(Long parentId) {
		// TODO Auto-generated method stub
		return sf.getCurrentSession().createQuery
				("from Department d where d.parent.id=?").setParameter(0, parentId).list();
	}

	@Override
	public List<Department> findAllClass() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Department d where d.parent.parent is not null ").list();
	}

	@Override
	public List<Department> findAllMyClass() {
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery("select distinct c.depts from Course c where c.user.id=? ").setParameter(0, user.getId()).list();
	}

	@Override
	public Department getByName(String dept, List<Department> departmentList) {
		Department find=new Department();
		System.out.println("====dept:"+dept);
		System.out.println("================长度为"+departmentList.size());
		for(Department all:departmentList)
		{
			System.out.println(all.getName());
			if(all.getName().equals(dept))
			{
				find=all;
				break;
			}
		}
		return find;
	}

	@Override
	public List<Department> findByMyCourse(Long courseId) {
		return getSession().createQuery("select depts from Course c where c.id=? ").setParameter(0, courseId).list();
	}

	@Override
	public List<Department> getByNames(String depts,
			List<Department> departmentList) {
		String dept[]=depts.split(" ");
		System.out.println("dept[]:"+dept[0]);
		System.out.println("=========dept.length:"+dept.length);
		List<Department> deptList=new ArrayList<Department>();
		for(int i=0;i<dept.length;i++)
		{
			deptList.add(getByName(dept[i], departmentList));
		}
		return deptList;
	}



}
