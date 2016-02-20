package com.njit.view.action;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.njit.base.BaseAction;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Experiment;
import com.njit.domain.Project;
import com.njit.domain.Report;
import com.njit.domain.Role;
import com.njit.domain.Task;
import com.njit.util.QueryHelper;
import com.njit.util.ResponseUtil;
import com.njit.util.gson.DeptGsonAdapter;
import com.njit.util.gson.ExperimentGsonAdapter;
import com.njit.util.gson.ProjectGsonAdapter;
import com.njit.util.gson.TaskGsonAdapter;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class TaskAction extends BaseAction<Task>{
	private Long courseId;
	private Long[] departmentIds;
	
	/** 列表 */
	public String list() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		
		new QueryHelper(Task.class, "t")
		.addCondition("t.user.id=? ", getCurrentUser().getId())
		.preparePageBean(reportService, pageNum, pageSize);
		return "list";
	}
	/** 列表 */
	public String listPart() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		if(courseId==0)
		{
			new QueryHelper(Task.class, "t")
			.addCondition("t.user.id=? ", getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}else{
			new QueryHelper(Task.class, "t")
			.addCondition("t.user.id=? ", getCurrentUser().getId())
			.addCondition(true,"t.course.id=? ", courseId)
			.preparePageBean(reportService, pageNum, pageSize);
		}
		
		
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		taskService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("departmentList", departmentList);
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		model.setCourse(cs.getById(courseId));
		List<Department> departmentList=ds.getByIds(departmentIds);
		System.out.println("=========="+departmentList.size());
		model.setDepts(new HashSet<Department>(departmentList));
		model.setUser(getCurrentUser());		
		model.setAssignDate(new Date());
//		SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
//		Date date = sdf.parse(model.getDueDate());
		taskService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Task task=taskService.getById(model.getId());
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("departmentList", departmentList);
		ActionContext.getContext().getValueStack().push(task);
		System.out.println("======="+task.getDueDate());
		if(task.getCourse()!=null)
		{
			courseId=task.getCourse().getId();
		}
		if(task.getDepts()!=null)
		{
			departmentIds=new Long[task.getDepts().size()];
			int index=0;
			for(Department dept:task.getDepts())
			{
				departmentIds[index++]=dept.getId();
			}
		}
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Task task=taskService.getById(model.getId());
		task.setCourse(cs.getById(courseId));
		task.setDescription(model.getDescription());
		task.setName(model.getName());
		task.setDueDate(model.getDueDate());
		List<Department> departmentList=ds.getByIds(departmentIds);
		task.setDepts(new HashSet<Department>(departmentList));
		
		taskService.update(task);
		return "toList";
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long[] getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(Long[] departmentIds) {
		this.departmentIds = departmentIds;
	}


	
}
