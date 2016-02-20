package com.njit.view.action;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.njit.base.BaseAction;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Project;
import com.njit.domain.Report;
import com.njit.domain.User;
import com.njit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ProjectAction extends BaseAction<Project>{
	private File upload;
	public String inputPath;
    /** Struts2约定的代表上传的文件的名 */  
    private String uploadFileName;  
    /** Struts2约定的代表上传文件的内容类型(MIME) */  
    private String uploadContentType;  
    private File fileUrl; //存放文件的文件名
    
	private static final long serialVersionUID = 1232131L;
	private String fileName;//此属性为接受前台界面传过来的属性名	
	private String fileNames;
	
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

	private Long curriculumId;
	/** 列表 */
	public String list() throws Exception {

		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		
		new QueryHelper(Project.class, "p")
		.preparePageBean(projectService, pageNum, pageSize);
		            
		return "list";
	}

	
	
	//模版and导入课程分配
		/** 导入 */
		public String projectInput() throws Exception {
			
			return "projectInput";
		}
		
		public List<Project> importProjectExcel(File pathName) {
			List<Project> projectList = new ArrayList<Project>();
			
			try {
				Workbook wb = Workbook.getWorkbook(pathName);
				Sheet st = wb.getSheet(0);
				int rows = st.getRows();
				System.out.println("===rows:"+rows);
				for (int i = 1; i < rows; i++) {
					Project project = new Project();				
					Curriculum cur=curriculumService.findByNo(st.getCell(0, i).getContents().trim());					
					project.setCurriculum(cur);
					project.setProjectNo(st.getCell(2, i).getContents().trim());
					project.setName(st.getCell(3, i).getContents().trim());
					project.setDescription(st.getCell(5, i).getContents().trim());
					project.setTotal(Integer.parseInt(st.getCell(4, i).getContents().trim()));
					
					projectList.add(project);
					project = null;
				}
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return projectList;
		}
		public String projectLogincalInput() throws Exception {
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
				List<Project> ul2 = importProjectExcel(fileUrl);
				for (Project u : ul2) {
					projectService.save(u);
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
//			HttpServletResponse response = ServletActionContext.getResponse();
//			response.setHeader("Content-Disposition", "attachment;fileName="
//					+ java.net.URLEncoder.encode(filename, "UTF-8"));

			/*
			 * 将文件地址转换成文件，然后转换成流,将流返回
			 */
			this.fileNames=java.net.URLEncoder.encode(filename, "UTF-8");
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

	/** 列表 */
	public String myList() throws Exception {

		List<Curriculum> curriculumList = curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		if (curriculumId == 0) {
			new QueryHelper(Project.class, "p").preparePageBean(projectService,
					pageNum, pageSize);
		} else {
			new QueryHelper(Project.class, "p")
			.addCondition(
					"p.curriculum.id=? ", curriculumId)
					
					.preparePageBean(
					projectService, pageNum, pageSize);
		}
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		projectService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		
		List<Curriculum> curriculumList=curriculumService.findAll();
		//ActionContext.getContext().getValueStack().push(getCurrentUser());
		ActionContext.getContext().put("curriculumList", curriculumList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		model.setCurriculum(curriculumService.getById(curriculumId));
		
		projectService.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Project project=projectService.getById(model.getId());
		//准备数据
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		

		ActionContext.getContext().getValueStack().push(project);
		//回显
		if(project.getCurriculum()!=null)
		{
			curriculumId=project.getCurriculum().getId();
		}
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Project project=projectService.getById(model.getId());
		project.setName(model.getName());
		project.setProjectNo(model.getProjectNo());
		project.setTotal(model.getTotal());
		project.setCurriculum(curriculumService.getById(curriculumId));
		project.setDescription(model.getDescription());
		projectService.update(project);
		return "toList";
	}

	public Long getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}

 
	
	
}
