package com.njit.view.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.njit.base.BaseAction;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Experiment;
import com.njit.domain.OldCourse;
import com.njit.domain.OldExperiment;
import com.njit.domain.Privilege;
import com.njit.domain.Project;
import com.njit.domain.Role;
import com.njit.domain.User;
import com.njit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
import com.sun.org.apache.bcel.internal.generic.GETSTATIC;

@Controller
@Scope("prototype")
public class CourseAction extends BaseAction<Course>{

	private Long userId;
	private Long[] departmentIds;
	private Long curriculumId;
	private String termName;
	private String fileNames;
	private File upload;
	public String inputPath;
    /** Struts2约定的代表上传的文件的名 */  
    private String uploadFileName;  
    /** Struts2约定的代表上传文件的内容类型(MIME) */  
    private String uploadContentType;  
    private File fileUrl; //存放文件的文件名
    
	private static final long serialVersionUID = 1232131L;
	private String fileName;//此属性为接受前台界面传过来的属性名	

	
	
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}
	public File getUpload() {
		return upload;
	}
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public String getInputPath() {
		return inputPath;
	}
	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}
	public String getUploadFileName() {
		return uploadFileName;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public String getUploadContentType() {
		return uploadContentType;
	}
	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public File getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(File fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileName() {
		return fileName;
	}
    public void setFileName(String fileName) throws Exception {

		/*
		 * 这里面转码的，原因是，前台传过来的形式是ISO-8859格式
		 * ，到后台我们还要转成UTF_8，避免中文乱码等原因
		 */
	 
		this.fileName = new String(fileName.getBytes("ISO-8859-1"),"UTF-8");
	}
	/** 列表 */
	public String arrList() throws Exception {
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		List<String> termList=cs.findAllTerm();
		ActionContext.getContext().put("termList", termList);
		new QueryHelper(Course.class, "c")
		.preparePageBean(cs, pageNum, pageSize);
		
		return "arrList";
	}
	
	//模版and导入课程分配
	/** 导入 */
	public String courseInput() throws Exception {
		
		return "courseInput";
	}
	
	public List<Course> importCourseExcel(File pathName) {
		List<Course> courseList = new ArrayList<Course>();
		
		try {
			Workbook wb = Workbook.getWorkbook(pathName);
			Sheet st = wb.getSheet(0);
			int rows = st.getRows();
			System.out.println("===rows:"+rows);
			for (int i = 1; i < rows; i++) {
				Course course = new Course();
				User user=us.findByLoginName(st.getCell(0, i).getContents().trim());
				System.out.println(user.getName());
				course.setUser(user);
				List<Department> departmentList = ds.findAll();
				List<Department> deptList=ds.getByNames(st.getCell(3, i).getContents()
						.trim(), departmentList);
				if (deptList != null) {
					course.setDepts(new HashSet<Department>(deptList));
				}
				Curriculum cur=curriculumService.getByName(st.getCell(2, i).getContents()
						.trim());
				course.setCurriculum(cur);
				course.setTerm(st.getCell(4, i).getContents()
						.trim());
				courseList.add(course);
				course = null;
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return courseList;
	}
	public String courseLogincalInput() throws Exception {
		String filePath = ServletActionContext.getServletContext()
				.getRealPath("/uploads/"+getCurrentUser().getLoginName());  		
		if(!new File(filePath).isDirectory()){
			//查看该路径存在与否，遇过不存在，创建路径
			new File(filePath).mkdirs();
		} 	        
		System.out.println("111111"+uploadFileName);
		fileUrl = new File(filePath+"/"+uploadFileName);
		FileUtils.copyFile(upload, fileUrl);//将文件复制到服务器上指定的路径      
		try {
			List<Course> ul2 = importCourseExcel(fileUrl);
			for (Course u : ul2) {
				cs.save(u);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "successUI";
	}
	
	/**
	 * 此方法对应struts.xml文件中的<param name="inputName">inputStream</param>属性
	 * 
	 * @return
	 * @throws Exception
	 */
	public InputStream getInputStream() throws Exception {
		/*
		 * 截取前台传过来的超链接地址后的文件名，作为显示用
		 */
		String filename = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		//System.out.println("=========+++++++++++++++++"+filename);
		/*
		 * 此处为点击下载后，提示框里显示文件的名字，正好是上面接截取的名字
		 */
//		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setHeader("Content-Disposition", "attachment;fileName="
//				+ java.net.URLEncoder.encode(filename, "UTF-8"));
this.fileNames=java.net.URLEncoder.encode(filename, "UTF-8");
		/*
		 * 将文件地址转换成文件，然后转换成流,将流返回
		 */
		fileName=ServletActionContext.getServletContext()
				.getRealPath("/uploads/10001")+"/"+fileName;
		File file = new File(fileName);
		InputStream is = new FileInputStream(file);
		return is;
	}

	/**
	 * 此方法是前台点击下载后的响应方法
	 * 
	 * @return
	 * @throws Exception
	 */
	public String filedownload() throws Exception {
		return "success";
	}

	
	
	//分页，查询
	/** 列表 */
	public String oldList() throws Exception {
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		List<String> termList=cs.findAllTerm();
		ActionContext.getContext().put("termList", termList);
		new QueryHelper(OldCourse.class, "c")
		.preparePageBean(ocs, pageNum, pageSize);		
		return "oldList";
	}
	public String delUI() throws Exception {
		return "del";
	}
	/** del */
	public String del() throws Exception {
		try {
			
			List<Course> courseList=cs.findAll();
			List<Experiment> expList=es.findAll();
			
			cs.deleteAll(courseList,expList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "successUI";
	}
	/** 部分列表 */
	public String arrListPart() throws Exception {
		//System.out.println("==================="+termName);
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		List<String> termList=cs.findAllTerm();
		ActionContext.getContext().put("termList", termList);
		if(curriculumId!=null){
			new QueryHelper(Course.class, "c")
			//.addCondition("c.termName=? ", termName)
			.addCondition("c.curriculum.id=? ", curriculumId)
			.preparePageBean(cs, pageNum, pageSize);
		}
		
		return "arrList";
	}
	
	/** 我的课程列表 */
	public String myList() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		cs.delete(model.getId());
		return "toList";
	}


	/** 添加页面 */
	public String arrAddUI() throws Exception {
		List<User> userList=us.findAllTeachers();
		System.out.println(userList);
		ActionContext.getContext().put("userList", userList);
		
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		
		List<Department> departmentList=ds.findAllClass();
		ActionContext.getContext().put("departmentList", departmentList);
		Date date=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		System.out.println("-------------------------"+c.get(Calendar.YEAR));
		ActionContext.getContext().put("year", c.get(Calendar.YEAR));
		return "arrSaveUI";
	}


	/** 添加 */
	public String arrAdd() throws Exception {
		User user=us.getById(userId);
		model.setUser(user);
		List<Department> departmentList=ds.getByIds(departmentIds);
		model.setDepts(new HashSet<Department>(departmentList));
		Curriculum curriculum=curriculumService.getById(curriculumId);
		model.setCurriculum(curriculum);
		
		cs.save(model);
		return "toList";
	}
	

	
	/** 修改页面 */
	public String arrEditUI() throws Exception {
		List<User> userList=us.findAllTeachers();
		ActionContext.getContext().put("userList", userList);
		
		List<Department> departmentList=ds.findAllClass();
		ActionContext.getContext().put("departmentList", departmentList);
		
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		
		Course course=cs.getById(model.getId());
		ActionContext.getContext().getValueStack().push(course);
		
		if(course.getDepts()!=null)
		{
			departmentIds=new Long[course.getDepts().size()];
			int index=0;
			for(Department department:course.getDepts())
			{
				departmentIds[index++]=department.getId();
			}
		}
		if(course.getUser()!=null)
		{
			userId=course.getUser().getId();
		}
		if(course.getCurriculum()!=null)
		{
			curriculumId=course.getCurriculum().getId();
		}
		Date date=new Date();
		Calendar c=Calendar.getInstance();
		c.setTime(date);
		System.out.println("-------------------------"+c.get(Calendar.YEAR));
		ActionContext.getContext().put("year", c.get(Calendar.YEAR));
		
		return "arrSaveUI";
	}



	/** 修改 */
	public String arrEdit() throws Exception {
		Course course=cs.getById(model.getId());
		
		List<Department> departmentList=ds.getByIds(departmentIds);
		course.setDepts(new HashSet<Department>(departmentList));
		
		User user=us.getById(userId);
		course.setUser(user);
		
		Curriculum curriculum=curriculumService.getById(curriculumId);
		course.setCurriculum(curriculum);
		System.out.println(model.getTerm()+"11111111111");
		course.setTerm(model.getTerm());
		cs.update(course);
		
		
		return "toArrList";
	}
 

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long[] getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(Long[] departmentIds) {
		this.departmentIds = departmentIds;
	}

	public Long getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String getTermName() {
		return termName;
	}
	public void setTermName(String termName) {
		this.termName = termName;
	}

	
	
	
	
	
}
