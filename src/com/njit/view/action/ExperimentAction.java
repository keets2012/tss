package com.njit.view.action;


import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.management.Query;
import javax.servlet.http.HttpServletResponse;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

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
import com.njit.domain.Laboratory;
import com.njit.domain.OldExperiment;
import com.njit.domain.PageBean;
import com.njit.domain.Project;
import com.njit.domain.Report;
import com.njit.domain.Task;
import com.njit.domain.User;
import com.njit.util.DepartmentUtils;
import com.njit.util.QueryHelper;
import com.njit.util.ResponseUtil;
import com.njit.util.Writer_excel;
import com.njit.util.gson.DeptGsonAdapter;
import com.njit.util.gson.ExperimentGsonAdapter;
import com.njit.util.gson.ProjectGsonAdapter;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ExperimentAction extends BaseAction<Experiment>{

	private String fileName;
	private Long projectId;
	private Long departmentId;
	private Long labId;
	private Long courseId;
	private Long userId;
	private Long curriculumId;
	private String type;
	private static Gson gson = new GsonBuilder()
	.registerTypeAdapter(Experiment.class, new ExperimentGsonAdapter())
	.registerTypeAdapter(Project.class, new ProjectGsonAdapter())
	.registerTypeAdapter(Department.class, new DeptGsonAdapter())
	.create() ;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	private int weekTime;
	private int dayTime;
	private int turnTime;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) throws Exception {
		this.fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
	}
	/** 列表 */
	public String list() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		System.out.println(courseList.size());
		new QueryHelper(Experiment.class, "e")
		.addCondition("e.user.id=? ", getCurrentUser().getId())
		.preparePageBean(es, pageNum, pageSize);


		return "list";
	}
	/** 列表 */
	public String oldList() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		System.out.println(courseList.size());
		new QueryHelper(OldExperiment.class, "e")
		.preparePageBean(oes, pageNum, pageSize);
		return "oldList";
	}
	//导出excel
	public String exportExcel() throws Exception {
		// TODO Auto-generated method stub
		List<Experiment> expList;

		String []title={"实验时间","学期","实验项目名称","实验课程","实验教师","实验机房","实验班级","实验说明"};
		if(this.getType().equals("single"))
		{
			expList=es.findByUser();
		}else{
			expList=es.findAll();
		}
        HttpServletResponse response = ServletActionContext.getResponse();
        Writer_excel.exportexcle_3(response,this.getFileName(),  
                expList,"课程表",title);       
	    return null;
	}
	/** 列表 */
	public String listPart() throws Exception {
		List<Course> courseList = cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		if (courseId == 0 && weekTime == 0) {
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.user.id=? ",
					getCurrentUser().getId()).preparePageBean(es, pageNum,
					pageSize);
		}
		if (courseId != 0 && weekTime != 0) {
			Course course = cs.getById(courseId);
			Long cId = course.getCurriculum().getId();
			new QueryHelper(Experiment.class, "e")
					.addCondition("e.user.id=? ", getCurrentUser().getId())
					.addCondition(true, "e.project.curriculum.id=? ", cId)
					.addCondition(true, "e.expTime like ? ", weekTime + "w%")
					.preparePageBean(es, pageNum, pageSize);
		}
		if (courseId == 0 && weekTime != 0) {
			new QueryHelper(Experiment.class, "e")
					.addCondition("e.user.id=? ", getCurrentUser().getId())
					.addCondition(true, "e.expTime like ? ", weekTime + "w%")
					.preparePageBean(es, pageNum, pageSize);
		}
		if (courseId != 0 && weekTime == 0) {
			Course course = cs.getById(courseId);
			Long cId = course.getCurriculum().getId();
			new QueryHelper(Experiment.class, "e")
					.addCondition("e.user.id=? ", getCurrentUser().getId())
					.addCondition(true, "e.project.curriculum.id=? ", cId)
					.preparePageBean(es, pageNum, pageSize);
		}

		return "list";
	}

	public String ajaxList() throws Exception{
		/*List<Experiment> experimentList=null;
		if("admin".equals(getCurrentUser().getLoginName()))
		{
			experimentList=es.findAll();
		}
		else{
			experimentList=es.findByUser();
		}*/
		QueryHelper helper = new QueryHelper(Experiment.class, "e")
		.addCondition("e.user.id=?", getCurrentUser().getId());
		PageBean pageBean = es.getPageBean(pageNum, pageSize, helper) ;
		String json = gson.toJson(pageBean) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json) ;
		return  null ;
	}
	/** 删除 */
	public String delete() throws Exception {
		es.delete(model.getId());
		return "toList";
	}
	/** 删除 */
	public String deleteCourse() throws Exception {
		es.delete(model.getId());
		return "toCourseList";
	}
	/** 删除 */
	public String ajaxDelete() throws Exception {
		es.delete(model.getId());
		ResponseUtil.write(ServletActionContext.getResponse(), true) ;
		return null;
	}

	//查询可用实验室
	public String queryLab() throws Exception {
		//?回显的数据
		
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
//		List<Project> projectList=projectService.findAll();
//		ActionContext.getContext().put("projectList", projectList);
		//List<Department> departmentList=ds.findAllMyClass();
		//ActionContext.getContext().put("departmentList", departmentList);
		return "queryUI";
	}
	
	
	public String query() throws Exception {
		String expTime=weekTime+"w"+dayTime+"w"+turnTime;
		List<Laboratory> allLabList=ls.findAll();
		List<Experiment> experimentList=es.findAll();
		List<Laboratory> laboratoryList=ls.findAvailLab(allLabList,experimentList,expTime);
		ActionContext.getContext().put("laboratoryList", laboratoryList);
		ActionContext.getContext().put("weekTime", weekTime);
		ActionContext.getContext().put("dayTime", dayTime);
		ActionContext.getContext().put("turnTime", turnTime);
		return "queryUI";
	}
	public String queryProject() throws Exception {
		//System.out.println("====================********************");
		Course course=cs.getById(courseId);
		Long curriculumId=course.getCurriculum().getId();
		//System.out.println("==========================="+curriculumId);
		List<Project> projectList=projectService.findByCourse(curriculumId);
		//System.out.println("====="+projectList.get(0).getName());
		//System.out.println("==========================="+projectList.size());
		String json = gson.toJson(projectList) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json) ;
		return null;
	}
	public String queryDepts() throws Exception {
		List<Department> deptList=ds.findByMyCourse(courseId);
		String json1 = gson.toJson(deptList) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json1) ;
		return null;
	}
	
	public String queryAvailLab() throws Exception{
		String expTime=weekTime+"w"+dayTime+"w"+turnTime;
		System.out.println(expTime);
		List<Laboratory> allLabList=ls.findAll();
		List<Experiment> experimentList=es.findAll();
		List<Laboratory> laboratoryList=ls.findAvailLab(allLabList,experimentList,expTime);
		System.out.println("===================");
		String json = gson.toJson(laboratoryList) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json) ;
		return null ;
	}
	
	/** 添加页面 */
	public String addUI() throws Exception {
		String expTime=weekTime+"w"+dayTime+"w"+turnTime;
		String expDiaplayTime = "第"+weekTime+"周，周"+dayTime+"，"+(turnTime*2-1)+"、"+(turnTime*2)+"节" ;
		//System.out.println("--->>"+model.getExpTime()+","+labId);
		Laboratory lab=ls.getById(labId);
		String labName=lab.getName();
		Long labId=lab.getId();
		ActionContext.getContext().put("labId", labId);
		ActionContext.getContext().put("labName", labName);
		ActionContext.getContext().put("expTime", expTime);
		ActionContext.getContext().put("expDisplayTime", expDiaplayTime);
		List<Project> projectList=projectService.findAll();
		ActionContext.getContext().put("projectList", projectList);
		List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("departmentList", departmentList);
		
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		try {
			String expTime=weekTime+"w"+dayTime+"w"+turnTime;
			System.out.println(expTime+","+projectId+","+departmentId);
			model.setExpTime(expTime);
			model.setProject(projectService.getById(projectId));
			model.setDept(ds.getById(departmentId));
			model.setLab(ls.getById(labId));
			model.setUser(getCurrentUser());
			Date date=new Date();
			Calendar c=Calendar.getInstance();
			c.setTime(date);
			//System.out.println("-------------------------"+c.get(Calendar.YEAR));
			int year=c.get(Calendar.YEAR);
			int month=c.get(Calendar.MONTH);
			System.out.println("+++++++======"+year+month);
			if(month<9&&month>1)
			{
				String term=(year-1)+"-"+year+"-2";
				System.out.println("--------------------"+term);
				model.setExpTerm(term);
			}else
			{
				String term=year+"-"+(year+1)+"-1";
				model.setExpTerm(term);
			}
			es.save(model);
			ResponseUtil.write(ServletActionContext.getResponse(), true) ;
		} catch (Exception e) {
			e.printStackTrace() ;
			ResponseUtil.write(ServletActionContext.getResponse(), "添加失败!") ;
		}
		return null;
	}

	/** 查看自己课表页面 */
	public String  myCourse() throws Exception {
		if("admin".equals(getCurrentUser().getName()))
		{
			new QueryHelper(Experiment.class, "e")
			.preparePageBean(es, pageNum, pageSize);
		}
		else if(getCurrentUser().hasPrivilegeByName("预约实验")){
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.user.id=? ", getCurrentUser().getId())
			.preparePageBean(es, pageNum, pageSize);
		}else{
			Department department=getCurrentUser().getDepartment();
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.dept.id=? ", department.getId())
			.preparePageBean(es, pageNum, pageSize);
		}
		
		return "myCourseUI";
	}  
	
	/** 查看自己课表页面 */
	public String  myCoursePart() throws Exception {
		if(weekTime==0)
		{
			if("admin".equals(getCurrentUser().getName()))
			{
				new QueryHelper(Experiment.class, "e")
				.preparePageBean(es, pageNum, pageSize);
			}
			else if(getCurrentUser().hasPrivilegeByName("预约实验")){
				new QueryHelper(Experiment.class, "e")
				.addCondition("e.user.id=? ", getCurrentUser().getId())
				.preparePageBean(es, pageNum, pageSize);
			}else{
				Department department=getCurrentUser().getDepartment();
				new QueryHelper(Experiment.class, "e")
				.addCondition("e.dept.id=? ", department.getId())
				.preparePageBean(es, pageNum, pageSize);
			}
			
		}
		else{
		if("admin".equals(getCurrentUser().getName()))
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.expTime like ? " , weekTime+"w%")
			.preparePageBean(es, pageNum, pageSize);
		}
		else if(getCurrentUser().hasPrivilegeByName("预约实验")){
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.user.id=? ", getCurrentUser().getId())
						.addCondition(true,"e.expTime like ? " , weekTime+"w%")
			.preparePageBean(es, pageNum, pageSize);
		}else{
			Department department=getCurrentUser().getDepartment();
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.dept.id=? ", department.getId())
			.addCondition(true,"e.expTime like ? " , weekTime+"w%")
			.preparePageBean(es, pageNum, pageSize);
		}}

		
		return "myCourseUI";
	} 
	/** 查看所有课表 */
	public String  course() throws Exception {
		new QueryHelper(Experiment.class, "e")
		.preparePageBean(es, pageNum, pageSize);
		List<User> userList=us.findAllTeachers();
		ActionContext.getContext().put("userList", userList);
		List<Department> departmentList=ds.findAllClass();
		ActionContext.getContext().put("departmentList", departmentList);
	
		return "courseUI";
	}
	/** 查看所有课表 */
	public String  coursePart() throws Exception {
		
		List<User> userList=us.findAllTeachers();
		ActionContext.getContext().put("userList", userList);
		List<Department> departmentList=ds.findAllClass();
		ActionContext.getContext().put("departmentList", departmentList);
		System.out.println(userId+"    "+departmentId+"    "+weekTime);
		if(userId==-1&&departmentId==-1&&weekTime==0)
		{
			new QueryHelper(Experiment.class, "e")
			.preparePageBean(es, pageNum, pageSize);
		}
		if(departmentId!=-1&&userId!=-1&&weekTime!=0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.user.id=? ", userId)
			.addCondition(true,"e.dept.id=? ", departmentId)
			.addCondition(true,"e.expTime like ? " , weekTime+"w%")
			.preparePageBean(es, pageNum, pageSize);
			
		}
		if(userId!=-1&&departmentId==-1&&weekTime==0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.user.id=? ", userId)
			.preparePageBean(es, pageNum, pageSize);
		}
		if(userId!=-1&&departmentId!=-1&&weekTime==0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.dept.id=? ", departmentId)
			.addCondition(true, "e.user.id=? ", userId)
			.preparePageBean(es, pageNum, pageSize);
		}
		if(userId==-1&&departmentId!=-1&&weekTime==0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.dept.id=? ", departmentId)
			.preparePageBean(es, pageNum, pageSize);
		}
		if(userId==-1&&departmentId==-1&&weekTime!=0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.expTime like ? " , weekTime+"w%")
			.preparePageBean(es, pageNum, pageSize);
		}
		if(userId==-1&&departmentId!=-1&&weekTime!=0)
		{
			new QueryHelper(Experiment.class, "e")
			.addCondition("e.expTime like ? " , weekTime+"w%")
			.addCondition(true,"e.dept.id=? ", departmentId)
			.preparePageBean(es, pageNum, pageSize);
		}
		

		return "courseUI";
	}
	
	/** 修改 */
	public String editUI() throws Exception {

		Experiment exp=es.getById(model.getId());
		ActionContext.getContext().getValueStack().push(exp);
		List<Laboratory> allLabList=ls.findAll();
		List<Experiment> experimentList=es.findAll();
		System.out.println(exp.getExpTime()+"   "+model.getId());
		List<Laboratory> labList=ls.findAvailLab(allLabList,experimentList,exp.getExpTime());
		labList.add(exp.getLab());
		ActionContext.getContext().put("labList", labList);
		int turn=Integer.parseInt(exp.getExpTime().split("w")[2]);
		System.out.println("------"+turn);
		String expDiaplayTime = "第"+exp.getExpTime().split("w")[0]+"周，周"+exp.getExpTime().split("w")[1]+"，"+(turn*2-1)+"、"+(turn*2)+"节" ;
		System.out.println(expDiaplayTime);
		ActionContext.getContext().put("expDiaplayTime", expDiaplayTime);
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		return "toList";
	}

	public Long getProjectId() {
		return projectId;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public Long getLabId() {
		return labId;
	}

	public void setLabId(Long labId) {
		this.labId = labId;
	}

	public int getWeekTime() {
		return weekTime;
	}

	public void setWeekTime(int weekTime) {
		this.weekTime = weekTime;
	}

	public int getDayTime() {
		return dayTime;
	}

	public void setDayTime(int dayTime) {
		this.dayTime = dayTime;
	}

	public int getTurnTime() {
		return turnTime;
	}

	public void setTurnTime(int turnTime) {
		this.turnTime = turnTime;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}

	
	
}
