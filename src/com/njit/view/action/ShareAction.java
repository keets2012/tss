package com.njit.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njit.base.BaseAction;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Reply;
import com.njit.domain.Share;
import com.njit.util.QueryHelper;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ShareAction extends BaseAction<Share> {

	private Long curriculumId;
	private Long courseId;
	/** 代表上传的文件内容的对象 */
	private File upload;
	private String fileNames;
	private boolean asc = false;

	/** Struts2约定的代表上传的文件的名 */
	private String uploadFileName;
	/** Struts2约定的代表上传文件的内容类型(MIME) */
	private String uploadContentType;
	private File fileUrl; // 存放文件的文件名

	private static final long serialVersionUID = 1232131L;
	private String fileName;// 此属性为接受前台界面传过来的属性名

	public String getFileNames() {
		return fileNames;
	}

	public void setFileNames(String fileNames) {
		this.fileNames = fileNames;
	}

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getFileName() {
		return fileName;
	}

	public File getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(File fileUrl) {
		this.fileUrl = fileUrl;
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

	public void setFileName(String fileName) throws Exception {

		/*
		 * 这里面转码的，原因是，前台传过来的形式是ISO-8859格式 ，到后台我们还要转成UTF_8，避免中文乱码等原因
		 */

		this.fileName = new String(fileName.getBytes("ISO-8859-1"), "UTF-8");
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
		// List<Share> shareList=ss.findByUser();
		// ActionContext.getContext().put("shareList", shareList);

		List<Course> courseList = cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		new QueryHelper(Share.class, "s").addCondition("s.user.id=? ",
				getCurrentUser().getId())
				.preparePageBean(ss, pageNum, pageSize);

		return "myList";
	}

	/** 列表 */
	public String myListPart() throws Exception {
		// List<Share> shareList=ss.findByUser();
		// ActionContext.getContext().put("shareList", shareList);

		List<Course> courseList = cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		if (courseId == 0) {
			new QueryHelper(Share.class, "s").addCondition("s.user.id=? ",
					getCurrentUser().getId()).preparePageBean(ss, pageNum,
					pageSize);
		} else {
			new QueryHelper(Share.class, "s")
					.addCondition("s.user.id=? ", getCurrentUser().getId())
					.addCondition("s.course.id=?", courseId)//
					.addOrderProperty("s.uploadTime", asc)//
					.preparePageBean(ss, pageNum, pageSize);
		}
		return "myList";
	}

	/** 列表 */
	public String list() throws Exception {
		List<Curriculum> curriculumList = curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		new QueryHelper(Share.class, "s")
				.preparePageBean(ss, pageNum, pageSize);

		return "list";
	}

	/** 列表 */
	public String listPart() throws Exception {
		List<Curriculum> curriculumList = curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		if (curriculumId == 0) {
			new QueryHelper(Share.class, "s").preparePageBean(ss, pageNum,
					pageSize);
		} else {
			new QueryHelper(Share.class, "s")//
					.addCondition("s.course.curriculum.id=?", curriculumId)//
					.addOrderProperty("s.uploadTime", asc)//
					.preparePageBean(ss, pageNum, pageSize);
		}
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		Share share = ss.getById(model.getId());
		String delpath = share.getFilePath();

		System.out.println(model.getId());
		System.out.println("==============>>>>>>>" + delpath);
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ss.delete(model.getId());

		return "toList";
	}

	/** 删除 */
	public String deleteAll() throws Exception {
		Share share = ss.getById(model.getId());
		String delpath = share.getFilePath();

		System.out.println(model.getId());
		System.out.println("==============>>>>>>>" + delpath);
		try {
			File file = new File(delpath);
			if (!file.isDirectory()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ss.delete(model.getId());

		return "toAllList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		List<Course> courseList = cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {

		String filePath = ServletActionContext.getServletContext().getRealPath(
				"/uploads/" + getCurrentUser().getLoginName());
		if (!new File(filePath).isDirectory()) {
			// 查看该路径存在与否，遇过不存在，创建路径
			new File(filePath).mkdirs();
		}
		//
		// System.out.println("文件的名：" + uploadFileName);
		// System.out.println("===" + upload.getName());
		// System.out.println("文件的内容类型：" + uploadContentType);

		fileUrl = new File(filePath + "/" + uploadFileName);
		FileUtils.copyFile(upload, fileUrl);// 将文件复制到服务器上指定的路径
		model.setCourse(cs.getById(courseId));
		model.setUser(getCurrentUser());
		model.setName(uploadFileName);
		model.setUploadTime(new Date());
		model.setFilePath(fileUrl.getAbsolutePath());
		ss.save(model);

		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		Share share = ss.getById(model.getId());
		List<Course> courseList = cs.findByUser();
		ActionContext.getContext().put("courseList", courseList);

		ActionContext.getContext().getValueStack().push(share);

		if (share.getCourse() != null) {
			courseId = share.getCourse().getId();
		}

		return "editUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		Share share = ss.getById(model.getId());
		share.setDescription(model.getDescription());
		share.setCourse(cs.getById(courseId));
		share.setUploadTime(new Date());
		ss.update(share);

		return "toList";
	}

	public Long getCurriculumId() {
		return curriculumId;
	}

	public void setCurriculumId(Long curriculumId) {
		this.curriculumId = curriculumId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
