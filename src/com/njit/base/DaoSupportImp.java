package com.njit.base;

import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import com.njit.domain.PageBean;
import com.njit.util.QueryHelper;

// @Transactional注解可以被继承
// @Transactional注解对父类中声明的方法无效
@Transactional
@SuppressWarnings("unchecked")
public class DaoSupportImp<T> implements DaoSupport<T>{

	@Resource

	private SessionFactory sf;
	private Class<T>clazz=null;
	protected Session getSession()
	{
		return sf.getCurrentSession();
	}
	public DaoSupportImp()
	{
		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
		this.clazz=(Class<T>) pt.getActualTypeArguments()[0];
	
	
	}
	
	@Override
	public void save(T entity) {
		// TODO Auto-generated method stub
		Session session=getSession();
		session.save(entity);
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		Object obj=getById(id);
		if(obj!=null)
		{
			getSession().delete(obj);
		}
	}

	@Override
	public void update(T entity) {
		// TODO Auto-generated method stub
		getSession().update(entity);
	}


	@Override
	public T getById(Long id) {
		// TODO Auto-generated method stub
		if(id==null)
		{
			return null;
		}else
		{
			return (T) getSession().get(clazz, id);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByIds(Long[] ids) {
		if (ids == null || ids.length == 0) {
			return Collections.EMPTY_LIST;
		} else {
			return getSession().createQuery(//
					"FROM " + clazz.getSimpleName() + " WHERE id IN (:ids)")//
					.setParameterList("ids", ids)//
					.list();
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from "+clazz.getSimpleName()).list();
	}
	// 公共的查询分页信息的方法（最终版）
		public PageBean getPageBean(int pageNum, int pageSize, QueryHelper queryHelper) {
			System.out.println("-------> DaoSupportImpl.getPageBean( int pageNum, int pageSize, QueryHelper queryHelper )");

			// 参数列表
			List<Object> parameters = queryHelper.getParameters();

			// 查询本页的数据列表
			Query listQuery = getSession().createQuery(queryHelper.getListQueryHql()); // 创建查询对象
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					listQuery.setParameter(i, parameters.get(i));
				}
			}
			listQuery.setFirstResult((pageNum - 1) * pageSize);
			listQuery.setMaxResults(pageSize);
			List list = listQuery.list(); // 执行查询

			// 查询总记录数量
			Query countQuery = getSession().createQuery(queryHelper.getCountQueryHql());
			if (parameters != null) { // 设置参数
				for (int i = 0; i < parameters.size(); i++) {
					countQuery.setParameter(i, parameters.get(i));
				}
			}
			Long count = (Long) countQuery.uniqueResult(); // 执行查询

			return new PageBean(pageNum, pageSize, count.intValue(), list);
		}
	

}
