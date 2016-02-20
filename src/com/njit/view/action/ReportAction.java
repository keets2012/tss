package com.njit.view.action;


import java.io.BufferedReader;
import java.io.File;
import com.njit.util.DocConverter;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.management.Query;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.njit.domain.Share;
import com.njit.domain.Task;
import com.njit.domain.User;
import com.njit.util.QueryHelper;
import com.njit.util.ResponseUtil;
import com.njit.util.Writer_excel;
import com.njit.util.gson.DeptGsonAdapter;
import com.njit.util.gson.ExperimentGsonAdapter;
import com.njit.util.gson.ProjectGsonAdapter;
import com.njit.util.gson.TaskGsonAdapter;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import jxl.*;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
@Controller
@Scope("prototype")
public class ReportAction extends BaseAction<Report>{
	private static Gson gson = new GsonBuilder()
	.registerTypeAdapter(Experiment.class, new ExperimentGsonAdapter())
	.registerTypeAdapter(Project.class, new ProjectGsonAdapter())
	.registerTypeAdapter(Task.class, new TaskGsonAdapter())
	.registerTypeAdapter(Department.class, new DeptGsonAdapter())
	.create() ;	
	public static final String UPLOAD_DIRECTORY = "upload";
	//protected HttpServletResponse response;
	private static final String ZIP_NAME = "batch_download_archives.zip";
	private Long curriculumId;
	private Long taskId;
	private Long courseId;
	private Long departmentId;
	private Long reportId;
	 /** 代表上传的文件内容的对象 */  
    private File upload;  
	private String deptTotal;
	private String subTotal;
	private String unSubTotal;
	private String onTimeSub;
    public Long getReportId() {
		return reportId;
	}
    
	public String getDeptTotal() {
		return deptTotal;
	}

	public void setDeptTotal(String deptTotal) {
		this.deptTotal = deptTotal;
	}

	public String getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}

	public String getUnSubTotal() {
		return unSubTotal;
	}

	public void setUnSubTotal(String unSubTotal) {
		this.unSubTotal = unSubTotal;
	}

	public String getOnTimeSub() {
		return onTimeSub;
	}

	public void setOnTimeSub(String onTimeSub) {
		this.onTimeSub = onTimeSub;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}
	private static final Logger LOGGER = LoggerFactory
			.getLogger(Report.class);
    /** Struts2约定的代表上传的文件的名 */  
    private String uploadFileName;  
    /** Struts2约定的代表上传文件的内容类型(MIME) */  
    private String uploadContentType;  
    private File fileUrl; //存放文件的文件名
    
	private static final long serialVersionUID = 1232131L;
	private String fileName;//此属性为接受前台界面传过来的属性名
	private String fileNames;
	private String courseName;
	private String deptName;
	private String taskName;
	private int downloadModel;
	private String[] downloadFile;
	/**
	 * Single file download model
	 */
	public static final int SINGLE_MODEL = 0;

	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) throws Exception {
		this.courseName = new String(courseName.getBytes("ISO-8859-1"),"UTF-8");
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) throws Exception {
		this.deptName = new String(deptName.getBytes("ISO-8859-1"),"UTF-8");
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) throws Exception {
		this.taskName = new String(taskName.getBytes("ISO-8859-1"),"UTF-8");
	}
	public String getFileNames() {
		return fileNames;
	}
	public void setFileNames(String fileNames) throws Exception {
		this.fileNames = new String(fileNames.getBytes("ISO-8859-1"),"UTF-8");
	}
	/**
	 * Multiple files download model
	 */
	public static final int MULTIPLE_MODEL = 1;
	
	/** 列表 */
	public String list() throws Exception {
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		new QueryHelper(Report.class, "r")
		.addCondition("r.user.id=? ", getCurrentUser().getId())
		.preparePageBean(reportService, pageNum, pageSize);
		return "list";
	}
	/** 列表 */
	public String listPart() throws Exception {
		System.out.println(curriculumId+"===========");
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		if(curriculumId==0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.user.id=? ", getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}else{
			System.out.println(curriculumId+"===========");
			new QueryHelper(Report.class, "r")
			.addCondition("r.user.id=? ", getCurrentUser().getId())
			.addCondition(true, "r.task.course.curriculum.id=? ", curriculumId)
			.preparePageBean(reportService, pageNum, pageSize);
		}
		
		return "list";
	}
	
	/** 报告统计 */
	public String count() throws Exception {
	
		List<Course> courseList=cs.findByUser();
		List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("courseList", courseList);
		ActionContext.getContext().put("departmentList", departmentList);
		//Long userId=getCurrentUser().getId();
		new QueryHelper(Report.class, "r")
		.addCondition("r.task.user.id=? ",getCurrentUser().getId())
		.preparePageBean(reportService, pageNum, pageSize);
		return "count";
	}

	/** 报告统计 */
	public String fileRead() throws Exception {

		// 读取request文件
		//fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		String filename = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		String converfilename = fileName.replaceAll("\\\\", "/");
		System.out.println("======="+converfilename);
		com.njit.util.DocConverter d=new com.njit.util.DocConverter(converfilename);
        d.conver();
        String swf=d.getswfPath();
       // swf=new String(swf.getBytes("ISO-8859-1"), "UTF-8");
         System.out.println("000000"+d.getswfPath());
         Report report=reportService.getById(reportId);
        ActionContext.getContext().getValueStack().push(report);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("swfpath", swf);
		return "view";
	}
	
	/** 报告统计 */
	public String fileReadNext() throws Exception {

		// 读取request文件
		//fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
		String filename = fileName.substring(fileName.lastIndexOf("\\") + 1,
				fileName.length());
		
		String converfilename = fileName.replaceAll("\\\\", "/");
		System.out.println("======="+converfilename);
		com.njit.util.DocConverter d=new com.njit.util.DocConverter(converfilename);
        d.conver();
        String swf=d.getswfPath();
       // swf=new String(swf.getBytes("ISO-8859-1"), "UTF-8");
         System.out.println("000000"+d.getswfPath());
         Report report=reportService.getById(reportId);
        ActionContext.getContext().getValueStack().push(report);
		HttpServletRequest request = ServletActionContext.getRequest();
		request.getSession().setAttribute("swfpath", swf);
		return "view";
	}
	//得分
	public String mark() throws Exception {
		Report report=reportService.getById(model.getId());
		report.setScore(model.getScore());		
		reportService.update(report);
		
		List<Course> courseList=cs.findByUser();
		List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("courseList", courseList);
		ActionContext.getContext().put("departmentList", departmentList);
		//Long userId=getCurrentUser().getId();
		new QueryHelper(Report.class, "r")
		.addCondition("r.task.user.id=? ",getCurrentUser().getId())
		.preparePageBean(reportService, pageNum, pageSize);
		return "count";
	}
	/** 列表 */
	public String myCount() throws Exception {
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		List<Task> taskList=taskService.findByUser();
		List<Department> departmentList=ds.findAllMyClass();

		ActionContext.getContext().put("taskList", taskList);
		ActionContext.getContext().put("departmentList", departmentList);
		if(courseId!=0&&taskId!=0&&departmentId!=0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.course.id=? ", courseId)
			.addCondition(true,"r.task.id=? ", taskId)
			.addCondition(true,"r.user.department.id=? ", departmentId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId==0&&taskId!=0&&departmentId!=0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.course.id=? ", courseId)
			.addCondition(true,"r.task.id=? ", taskId)
			.addCondition(true,"r.user.department.id=? ", departmentId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId==0&&taskId==0&&departmentId!=0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.user.id=? ",getCurrentUser().getId())
			.addCondition(true,"r.user.department.id=? ", departmentId)
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId==0&&taskId==0&&departmentId==0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.user.id=? ",getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId!=0&&taskId==0&&departmentId==0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.course.id=? ", courseId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId!=0&&taskId!=0&&departmentId==0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.course.id=? ", courseId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.addCondition(true,"r.task.id=? ", taskId)
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId!=0&&taskId==0&&departmentId!=0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.course.id=? ", courseId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.addCondition(true,"r.task.id=? ", taskId)
			.addCondition(true,"r.user.department.id=? ", departmentId)
			.preparePageBean(reportService, pageNum, pageSize);
		}
		if(courseId==0&&taskId!=0&&departmentId==0)
		{
			new QueryHelper(Report.class, "r")
			.addCondition("r.task.id=? ", taskId)
			.addCondition(true,"r.task.user.id=? ",getCurrentUser().getId())
			.preparePageBean(reportService, pageNum, pageSize);
		}
		return "count";
	}
	
	/** 报告名单统计 */
	public String detailCountUI() throws Exception {
	
		List<Course> courseList=cs.findByUser();
		//List<Department> departmentList=ds.findAllMyClass();
		ActionContext.getContext().put("courseList", courseList);
	//	ActionContext.getContext().put("departmentList", departmentList);
		
		return "detaileCount";
	}
	
	/** 报告名单统计 */
	public String detailCount() throws Exception {
	
		System.out.println("---------"+courseId+"  "+departmentId+"   "+taskId);
		List<Course> courseList=cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		//获取班级
		Department department=ds.getById(departmentId);
		ActionContext.getContext().put("deptName",department.getName() );
		//获取任务名称
		Task task=taskService.getById(taskId);
		ActionContext.getContext().put("taskName", task.getName());
		//获取课程名称
		Course course=cs.getById(courseId);
		ActionContext.getContext().put("courseName", course.getCurriculum().getName());		
		int deptTotal=us.queryTotal(departmentId);
		ActionContext.getContext().put("deptTotal", deptTotal);
		int subTotal=reportService.querySub(departmentId,taskId);
		ActionContext.getContext().put("subTotal", subTotal);
		int unSubTotal=deptTotal-subTotal;
		ActionContext.getContext().put("unSubTotal", unSubTotal);
		int onTimeSub=reportService.queryOnTimeSub(departmentId,taskId);
		ActionContext.getContext().put("onTimeSub", onTimeSub);
		List<User> subList=reportService.querySubUser(departmentId,taskId);
		
		List<User> unSubList=us.findClassStudent(departmentId,subList);		
		ActionContext.getContext().put("unSubList", unSubList);
		List<Report> subReportList=reportService.findSub(departmentId,taskId);
		
		List<User> outDateSubList=reportService.queryOutDate(subReportList);
		ActionContext.getContext().put("outDateSubList", outDateSubList);
		System.out.println("---------++++++++"+deptTotal+"  "+subTotal+"   "+onTimeSub);
		return "detaileCountShow";
	}
	
	//生成excel
	 public String Writer_excel()
	 {
	        //标题行
	        String title[]={"角色","编号","功能名称","功能描述"};
	        //内容
	        String context[][]={{"UC11","设置课程","创建课程"},
	                            {"UC12","设置学生名单","给出与课程关联的学生名单"},
	                            {"UC21","查看学生名单",""},
	                            {"UC22","查看小组信息","显示助教所负责的小组列表信息"}
	                            };
	        //操作执行
	        try { 
	            //t.xls为要新建的文件名
	            WritableWorkbook book= Workbook.createWorkbook(new File("t.xls")); 
	            //生成名为“第一页”的工作表，参数0表示这是第一页 
	            WritableSheet sheet=book.createSheet("第一页",0); 
	            
	            //写入内容
	            for(int i=0;i<4;i++)    //title
	                sheet.addCell(new Label(i,0,title[i])); 
	            for(int i=0;i<4;i++)    //context
	            {
	                for(int j=0;j<3;j++)
	                {
	                    sheet.addCell(new Label(j+1,i+1,context[i][j])); 
	                }
	            }
	            sheet.addCell(new Label(0,1,"教师"));
	            sheet.addCell(new Label(0,3,"助教"));
	            
	            /*合并单元格.合并既可以是横向的，也可以是纵向的
	             *WritableSheet.mergeCells(int m,int n,int p,int q);   表示由(m,n)到(p,q)的单元格组成的矩形区域合并
	             * */
	            sheet.mergeCells(0,1,0,2);
	            sheet.mergeCells(0,3,0,4);
	            
	            //写入数据
	            book.write(); 
	            //关闭文件
	            book.close(); 
	        }
	        catch(Exception e) { 
	        	e.printStackTrace();
	        } 
	        return null;
	        }
	
	
	public String queryDepts() throws Exception {
		System.out.println("==========++++++++"+courseId);
		courseId=taskService.getById(taskId).getCourse().getId();
		List<Department> departmentList=cs.findByCourse(courseId);

		System.out.println("=========="+departmentList.size());
		String json = gson.toJson(departmentList) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json) ;
		return null;
	}
	
	
	public String queryTask() throws Exception {
		List<Task> taskList=taskService.findByCourse(courseId);
		//System.out.println("=========="+taskList.size());
		String json = gson.toJson(taskList) ;
		ResponseUtil.write(ServletActionContext.getResponse(), json) ;
		return null;
	}
	
	/** 列表 */
	public String myList() throws Exception {
		List<Report> reportList=reportService.findByUser();
		List<Task> allTaskList=taskService.findByStudent();
		List<Task> taskList=taskService.findUnhandle(reportList,allTaskList);
		ActionContext.getContext().put("taskList", taskList);
		
		return "myList";
	}

	/** 删除 */
	public String delete() throws Exception {
		Report report=reportService.getById(model.getId());
		String delpath = report.getFilePath();
		
		System.out.println(model.getId());
		System.out.println("==============>>>>>>>"+delpath);
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		reportService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		Task task=taskService.getById(taskId);
		ActionContext.getContext().put("courseName", task.getCourse().getCurriculum().getName());
		ActionContext.getContext().put("userName", task.getUser().getName());
		ActionContext.getContext().put("taskName", task.getName());
		ActionContext.getContext().put("thisId", taskId);
		System.out.println("================="+taskId);
		
		return "saveUI";
	}
	public String getRealPath(String pathName) {
		return ServletActionContext.getServletContext().getRealPath(pathName) + "/";
	}
	/** 添加 */
	public String add() throws Exception {
		
		String filePath = ServletActionContext.getServletContext().getRealPath("/uploads/"+getCurrentUser().getLoginName());  		
		if(!new File(filePath).isDirectory()){
			//查看该路径存在与否，遇过不存在，创建路径
			new File(filePath).mkdirs();
		}
		
		System.out.println("文件的名：" + uploadFileName);  
	    System.out.println("===" + upload.getName());  
	    System.out.println("文件的内容类型：" + uploadContentType);  
	        
		fileUrl = new File(filePath+"/"+uploadFileName);
		FileUtils.copyFile(upload, fileUrl);//将文件复制到服务器上指定的路径      
		
		model.setUser(getCurrentUser());
		model.setDescription(model.getDescription());
		model.setName(model.getName());
		model.setFilePath(fileUrl.getAbsolutePath());
		model.setTask(taskService.getById(taskId));
		model.setHandleDate(new Date());
		reportService.save(model);
		
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Report report=reportService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(report);
		
		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Report report=reportService.getById(model.getId());
		report.setName(model.getName());
		report.setDescription(model.getDescription());
		
		reportService.update(report);
		
		return "toList";
	}

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
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

	public String exportExcel() throws Exception {
		// TODO Auto-generated method stub
		System.out.println("---------"+this.getCourseName()+"  "+this.getDeptName()+"   "+this.getTaskName());
		List<Department> deptList=ds.findAll();
		Department dep=ds.getByName(this.getDeptName(), deptList);
		List<Task> taskList=taskService.findAll();
		Task task=taskService.getByName(this.getTaskName(),taskList);
//		List<Course> courseList=cs.findAll();
//		Course course=cs.getByName(this.getCourseName(),courseList);
		List<User> subList=reportService.querySubUser(dep.getId(),task.getId());
		
		List<User> unSubList=us.findClassStudent(dep.getId(),subList);		
		String unSub="";
		for(User user:unSubList)
		{
			unSub=unSub+user.getLoginName()+" "+user.getName()+"  ";
			
		}
		List<Report> subReportList=reportService.findSub(dep.getId(),task.getId());
		
		List<User> outDateSubList=reportService.queryOutDate(subReportList);
		String  outDate="";
		for(User user:outDateSubList)
		{
			outDate=outDate+user.getLoginName()+" "+user.getName()+"  ";
		}
		File file = new File(this.getFileName());
        WritableWorkbook wwb;  
            //根据传进来的file对象创建可写入的Excel工作薄  
         wwb = Workbook.createWorkbook(file);  
            /* 
             * 创建一个工作表、sheetName为工作表的名称、"0"为第一个工作表 
             * 打开Excel的时候会看到左下角默认有3个sheet、"sheet1、sheet2、sheet3"这样 
             * 代码中的"0"就是sheet1、其它的一一对应。 
             * createSheet(sheetName, 0)一个是工作表的名称，另一个是工作表在工作薄中的位置 
             */  
            WritableSheet ws = wwb.createSheet("名单统计", 0);    
            //创建单元格样式  
            WritableCellFormat wcf = new WritableCellFormat();   
            //背景颜色设置为"那什么"色  
            wcf.setBackground(Colour.YELLOW); 
            wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
            /* 
             * 这个是单元格内容居中显示 
             * 还有很多很多样式 
             */  
            wcf.setAlignment(Alignment.CENTRE);  
            jxl.write.WritableFont wfcontent =new jxl.write.WritableFont(WritableFont.ARIAL,12, WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.GREEN);
            WritableCellFormat wcfcontent = new WritableCellFormat(wfcontent);
            wcfcontent.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN,jxl.format.Colour.BLACK); //BorderLineStyle边框
            wcfcontent.setAlignment(Alignment.LEFT);
            wcfcontent.setWrap(true);
            CellView cellView = new CellView();  
            cellView.setAutosize(true); //设置自动大小
  
            //判断一下表头数组是否有数据                       
                   /* 
                     * 添加单元格(Cell)内容addCell() 
                     * 添加Label对象Label() 
                     * 数据的类型有很多种、在这里你需要什么类型就导入什么类型 
                     * 如：jxl.write.DateTime 、jxl.write.Number、jxl.write.Label 
                     * Label(i, 0, columns[i], wcf) 
                     * 其中i为列、0为行、columns[i]为数据、wcf为样式 
                     * 合起来就是说将columns[i]添加到第一行(行、列下标都是从0开始)第i列、样式为什么"色"内容居中 
                     */  
            ws.setRowView(7, 900, false); //设置行高
            ws.setRowView(8, 900, false);
            ws.setColumnView(0, 15);
            ws.setColumnView(1, cellView);//根据内容自动设置列宽 
           // ws.setColumnView(7, unSub.length());  
               ws.addCell(new Label(0, 0, "班级名称" , wcf)); 
               ws.addCell(new Label(0, 1, "课程名称" , wcf));
               ws.addCell(new Label(0, 2, "任务名称" , wcf));
               ws.addCell(new Label(0, 3, "应交数量" , wcf));
               ws.addCell(new Label(0, 4, "已交数量" , wcf));
               ws.addCell(new Label(0, 5, "未交数量" , wcf));
               ws.addCell(new Label(0, 6, "按时提交数量" , wcf));
               ws.addCell(new Label(0, 7, "尚未提交名单" , wcf));
               ws.addCell(new Label(0, 8, "未按时提交名单" , wcf));        
	           //写入Exel工作表  
               
               ws.addCell(new Label(1, 0, this.getDeptName() , wcfcontent)); 
               ws.addCell(new Label(1, 1, this.getCourseName(), wcfcontent));
               ws.addCell(new Label(1, 2, this.getTaskName(), wcfcontent));
               ws.addCell(new Label(1, 3, this.getDeptTotal(), wcfcontent ));
               ws.addCell(new Label(1, 4, this.getSubTotal() , wcfcontent));
               ws.addCell(new Label(1, 5, this.getUnSubTotal(), wcfcontent));
               ws.addCell(new Label(1, 6, this.getOnTimeSub(), wcfcontent));
               ws.addCell(new Label(1, 7, unSub , wcfcontent));
               ws.addCell(new Label(1, 8, outDate , wcfcontent)); 
	           
               wwb.write();  
	 
	               //关闭Excel工作薄对象   
	           wwb.close();     
	           HttpServletResponse response = ServletActionContext.getResponse();
	           File f=null;  
	           try {  
	               //根据刚刚的文件地址、创建一个file对象  
	               f = new File(this.getFileName());  
	     
	               //如果文件不存在  
	               if (!f.exists()) {  
	                   response.sendError(404, "File not found!");  
	               }  
	     
	               //创建一个缓冲输入流对象  
	               BufferedInputStream br = new BufferedInputStream(  
	                       new FileInputStream(f));  
	               byte[] buf = new byte[1024];  
	               int len = 0;  
	     
	               response.reset(); // 非常重要  
	               response.setContentType("application/x-msdownload");  
	               response.setHeader("Content-Disposition", "attachment; filename="  
	                       + f.getName());  
	                 
	               //创建输出流对象  
	               OutputStream outStream = response.getOutputStream();  
	     
	               //开始输出  
	               while ((len = br.read(buf)) > 0)  
	                   outStream.write(buf, 0, len);  
	     
	               //关闭流对象  
	               br.close();  
	               outStream.close();  
	           } catch (FileNotFoundException e) {  
	               e.printStackTrace();  
	           } catch (IOException e) {  
	               e.printStackTrace();  
	           }  
	           if (f.exists()) {//下载完毕删除文件  
	               f.delete();  
	           }    
        
	           return null;
	}
    
/**
 * 此方法对应struts.xml文件中的<param name="inputName">inputStream</param>属性
 * @return
 * @throws Exception
 */
	public InputStream getInputStream() throws Exception {
		/*
		 * 截取前台传过来的超链接地址后的文件名，作为显示用
		 */
		if (downloadModel == MULTIPLE_MODEL) {
			//InputStream fis=null;
			
			InputStream fis=null;
			System.out.println("++++++++++++===="+downloadFile.length);
			HttpServletResponse response = ServletActionContext.getResponse();
			//response.setHeader("Content-Disposition", "attachment;fileName="
			//		+ java.net.URLEncoder.encode(ZIP_NAME, "UTF-8"));
			this.fileNames=java.net.URLEncoder.encode(ZIP_NAME, "UTF-8");
			ZipOutputStream out = new ZipOutputStream(
					response.getOutputStream(), Charset.forName("utf-8"));// Need
																				// JDK_7
			for (int i = 0, length = downloadFile.length; i < length; i++) {
				downloadFile[i] = new String(
						downloadFile[i].getBytes("ISO8859-1"), "UTF-8");
				String downPath=downloadFile[i].substring(
						downloadFile[i].lastIndexOf("\\") + 1, downloadFile[i].length());
				//System.out.println("============="+downPath);
				File download = new File(downloadFile[i]);
				fis = new FileInputStream(download);
				System.out.println("============="+download.getName());
				ZipEntry entry = new ZipEntry(download.getName());
				out.putNextEntry(entry);
				io(fis, out, 10240);
				out.closeEntry();
			//fis.close();
			}

			out.flush();
			out.close();
			LOGGER.info(ZIP_NAME + " create success!");
			return fis;
		}else 
		{
			//System.out.println("1===========-------------------");
		String filename = fileName.substring(
				fileName.lastIndexOf("\\") + 1, fileName.length());
		/*
		 * 此处为点击下载后，提示框里显示文件的名字，正好是上面接截取的名字
		 */
		//System.out.println("-------------"+filename);
		HttpServletResponse response = ServletActionContext.getResponse();
//		response.setHeader("Content-Disposition", "attachment;fileName="
//				+ java.net.URLEncoder.encode(filename, "UTF-8"));
		/*
		 * 将文件地址转换成文件，然后转换成流,将流返回
		 */
		this.fileNames=java.net.URLEncoder.encode(filename, "UTF-8");
		File file = new File(fileName);
		InputStream is = new FileInputStream(file);
		return is;
		}
	}
	
	
	public static boolean io(InputStream input, OutputStream output,
			int bufferSize) {
		byte[] buf = new byte[bufferSize > 0 ? bufferSize : 2048];
		int len = 0;
		try {
			while ((len = input.read(buf)) != -1) {
				output.write(buf, 0, len);
			}
			output.flush();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * 此方法是前台点击下载后的响应方法
	 * @return
	 * @throws  Exception
	 */
	public String filedownload() throws Exception {
		downloadModel=SINGLE_MODEL;
		return "success";
	}
	public String multiFileDownload() throws Exception {
		downloadModel=MULTIPLE_MODEL;
		return "success";
	}
	
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	public Long getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}
	public int getDownloadModel() {
		return downloadModel;
	}
	public void setDownloadModel(int downloadModel) {
		this.downloadModel = downloadModel;
	}
	public String[] getDownloadFile() {
		return downloadFile;
	}
	public void setDownloadFile(String[] downloadFile) {
		this.downloadFile = downloadFile;
	}
	
	
}
