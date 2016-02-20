package com.njit.base;

import java.lang.reflect.ParameterizedType;

import javax.annotation.Resource;

import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Experiment;
import com.njit.domain.User;
import com.njit.service.CourseService;
import com.njit.service.CurriculumService;
import com.njit.service.DepartmentService;
import com.njit.service.ExperimentService;
import com.njit.service.ForumService;
import com.njit.service.LaboratoryService;
import com.njit.service.OldCourseService;
import com.njit.service.OldExperimentService;
import com.njit.service.PrivilegeService;
import com.njit.service.ProjectService;
import com.njit.service.ReplyService;
import com.njit.service.ReportService;
import com.njit.service.RoleService;
import com.njit.service.ShareService;
import com.njit.service.TaskService;
import com.njit.service.TopicService;
import com.njit.service.UserService;
import com.njit.view.action.ShareAction;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public abstract class BaseAction<T> extends ActionSupport implements ModelDriven<T>{

	@Resource 
	protected CurriculumService curriculumService;
	@Resource
	protected ReportService reportService;
	@Resource
	protected TaskService taskService;
	@Resource
	protected ShareService ss;
	@Resource
	protected ProjectService projectService;
	@Resource
	protected ExperimentService es;
	@Resource
	protected CourseService cs;
	@Resource
	protected TopicService ts;
	@Resource
	protected LaboratoryService ls;
	
	@Resource
	protected ReplyService replyService;
	@Resource
	protected ForumService fs;
	
	@Resource
	protected UserService us;
	@Resource
	public RoleService rs;
	
	@Resource
	protected DepartmentService ds;
	
	@Resource
	protected PrivilegeService ps;
	
	@Resource
	protected OldCourseService ocs;
	
	@Resource
	protected OldExperimentService oes;
	
	protected int pageNum=1;
	protected int pageSize=10;
	
	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	protected User getCurrentUser()
	{
		return (User) ActionContext.getContext().getSession().get("user");
	}
	public T model;
	public BaseAction()
	{
		try {
			ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
			
			Class<T> clazz=(Class<T>) pt.getActualTypeArguments()[0];
			model=clazz.newInstance();
		} catch (Exception e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
	}
	


	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return this.model;
	}
}
