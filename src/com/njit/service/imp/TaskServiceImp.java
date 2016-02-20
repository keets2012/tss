package com.njit.service.imp;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Department;
import com.njit.domain.Report;
import com.njit.domain.Task;
import com.njit.domain.User;
import com.njit.service.TaskService;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("unchecked")

@Service
@Transactional
public class TaskServiceImp extends DaoSupportImp<Task> implements TaskService{

	
	@Override
	public List<Task> findByUser() {
		// TODO Auto-generated method stub
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery("from Task t where t.user.id=?").setParameter(0, user.getId()).list();
	}

	@Override
	public List<Task> findByStudent() {
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery("select d.tasks from Department d where d.id=? ")
				.setParameter(0, user.getDepartment().getId()).list();
	}

	@Override
	public List<Task> findUnhandle(List<Report> reportList,
			List<Task> allTaskList) {
		List<Task> myTaskList=new ArrayList<Task>();
		List<Task> delList = new ArrayList<Task>();
		for(Report report:reportList)
		{
			myTaskList.add(report.getTask());
		}
		if (myTaskList != null) {
			for (Task allTask : allTaskList) {
				for (Task myTask : myTaskList) {
					if (allTask.getId() == myTask.getId()) {
						delList.add(allTask);
						break;
					}
				}
			}
			allTaskList.removeAll(delList);
		}
		return allTaskList;

	}

	@Override
	public List<Task> findByCourse(Long courseId) {
		
		return getSession().createQuery("from Task t where t.course.id=? ")
				.setParameter(0, courseId).list();
	}

	@Override
	public Task getByName(String taskName, List<Task> taskList) {
		Task find=new Task();
		System.out.println("====dept:"+taskName);
		System.out.println("================长度为"+taskList.size());
		for(Task all:taskList)
		{
			System.out.println(all.getName());
			if(all.getName().equals(taskName))
			{
				find=all;
				break;
			}
		}
		return find;
	}




}
