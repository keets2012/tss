package com.njit.service.imp;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Report;
import com.njit.domain.User;
import com.njit.service.ReportService;
import com.opensymphony.xwork2.ActionContext;
@SuppressWarnings("unchecked")
@Service
@Transactional
public class ReportServiceImp extends DaoSupportImp<Report> implements ReportService{

	
	@Override
	public List<Report> findByUser() {
		// TODO Auto-generated method stub
		User user=(User) ActionContext.getContext().getSession().get("user");
		return getSession().createQuery("from Report r where r.user.id=? ")
				.setParameter(0, user.getId())
				.list();
	}

	@Override
	public List<Report> findByTeacher() {
		User user=(User) ActionContext.getContext().getSession().get("user");
		return getSession().createQuery("from Report r where r.task.user.id=? ")
				.setParameter(0, user.getId())
				.list();
	}

	@Override
	public int querySub(Long departmentId, Long taskId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Report r where r.user.department.id=? and r.task.id=? ")
				.setParameter(0, departmentId).setParameter(1, taskId).list().size();
	}

	@Override
	public int queryOnTimeSub(Long departmentId, Long taskId) {
		return getSession().createQuery("from Report r " +
				"where r.user.department.id=? and r.task.id=? and r.handleDate<r.task.dueDate")
				.setParameter(0, departmentId).setParameter(1, taskId).list().size();
	}

	@Override
	public List<User> querySubUser(Long departmentId, Long taskId) {
		return getSession().createQuery("select r.user from Report r where r.user.department.id=? and r.task.id=? ")
				.setParameter(0, departmentId).setParameter(1, taskId).list();
	}

	@Override
	public List<User> queryOutDate(List<Report> subReportList) {
		List<Report> temp=new ArrayList<Report>();
		for(Report report:subReportList)
		{
			if(!compare_date(report.getHandleDate(), report.getTask().getDueDate()))
			{
				temp.add(report);
			}
		}
		subReportList.removeAll(temp);
		List<User> userList=new ArrayList<User>();
		for(Report rep:subReportList)
		{
			User user=rep.getUser();
			userList.add(user);
		}
		return userList;
	}

	@Override
	public List<Report> findSub(Long departmentId, Long taskId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Report r where r.task.id=? and r.user.department.id=? ")
				.setParameter(0, taskId).setParameter(1, departmentId).list();
	}
	
	
    public static boolean compare_date(Date DATE1, Date DATE2) {
        
        boolean b=true;
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (DATE1.getTime() > DATE2.getTime()) {
                System.out.println("dt1 在dt2前");
                b=true;
            } else  {
            	b=false;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return b;
    }




}
