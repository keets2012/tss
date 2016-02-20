package com.njit.service.imp;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Course;
import com.njit.domain.User;
import com.njit.service.UserService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
public class UserServiceImp extends DaoSupportImp<User> implements UserService{

	@Override
	public User findByLoginNameAndPassword(String loginName, String password) {
		// TODO Auto-generated method stub
		String md5Digest=DigestUtils.md5Hex(password);

		return (User) getSession().createQuery("from User u where u.loginName=? and u.password=?").setParameter
				(0, loginName).setParameter(1, md5Digest).uniqueResult();
	}


	@Override
	public List<User> findAllTeachers() {
		// TODO Auto-generated method stub
		return getSession().createQuery("select r.users from Role r where r.name = ?").setString(0, "教师").list();
//		return getSession().createSQLQuery("").addEntity(User.class).list() ;
	}

	@Override
	public int queryTotal(Long departmentId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from User u where u.department.id=? ")
				.setParameter(0, departmentId).list().size();
	}


	@Override
	public List<User> findClassStudent(Long departmentId, List<User> subList) {
		List<User> userList=getSession().createQuery("from User u where u.department.id=? ")
				.setParameter(0, departmentId).list();
		List<User> temp=new ArrayList<User>();
		for(User allUser:userList)
		{
			for(User subUser:subList)
			{
				if(allUser.getId()==subUser.getId())
				{
					temp.add(allUser);
					break;
				}
			}
		}
		userList.removeAll(temp);
		return userList;
	}


	@Override
	public List<Course> findMyCourse() {
		// TODO Auto-generated method stub
		User user=(User) ActionContext.getContext().getSession().get("user");
		return getSession().createQuery("select u.courses from User u where u.id=? ")
				.setParameter(0, user.getId()).list();
	}


	@Override
	public User findByLoginName(String loginName) {
		// TODO Auto-generated method stub
		System.out.println("loginName:"+loginName);
		return (User) getSession().createQuery("from User u where u.loginName=? ").setParameter
				(0, loginName).uniqueResult();
	}


}
