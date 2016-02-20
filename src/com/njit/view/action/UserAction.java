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

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import com.njit.base.BaseAction;
import com.njit.base.DaoSupport;
import com.njit.domain.Department;
import com.njit.domain.Role;
import com.njit.domain.User;
import com.njit.util.DepartmentUtils;
import com.njit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;

@Repository
@Scope("prototype")
@Controller
public class UserAction extends BaseAction<User>{
	private Long departmentId;
	private Long[] roleIds;
	private String oldPassword;
	private File upload;
	private int type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String list() throws Exception {
//		List<User> userList=us.findAll();
//		ActionContext.getContext().put("userList", userList);
		
		List<Department> topList=ds.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		new QueryHelper(User.class, "u")
		.preparePageBean(us, pageNum, pageSize);
		return "list";
	}

	public List<User> importUserExcel(File pathName, int flag) {
		List<User> stuList = new ArrayList<User>();
		List<Role> roleList = new ArrayList<Role>();
		try {
			Workbook wb = Workbook.getWorkbook(pathName);
			Sheet st = wb.getSheet(0);
			int rows = st.getRows();
			System.out.println("-------------"+rows);
			for (int i = 1; i < rows; i++) {
				User u = new User();
				// 学生

				List<Department> departmentList = ds.findAll();
				u.setLoginName(st.getCell(0, i).getContents());
				u.setName(st.getCell(1, i).getContents().trim());
				u.setGender(st.getCell(2, i).getContents());
				String md5Digest=DigestUtils.md5Hex("1234");
				u.setPassword(md5Digest);
				System.out.println("======="+st.getCell(3, i).getContents()
						.trim());
				Department dept = ds.getByName(st.getCell(3, i).getContents()
						.trim(), departmentList);
				if (dept != null) {
					u.setDepartment(dept);
				}

				if (flag == 0) {
					Role stu = rs.getById(12l);
					roleList.add(stu);
					u.setRoles(new HashSet<Role>(roleList));
					roleList.remove(0);
				}

				if (flag == 1) { // 教师					
					Role teacher = rs.getById(11l);
					roleList.add(teacher);
					u.setRoles(new HashSet<Role>(roleList));
				}
				stuList.add(u);
				u = null;
			}
		} catch (BiffException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stuList;
	}

	public String userInput() throws Exception {
		return "userInput";
	}
	public String userLogincalInput() throws Exception {
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
			List<User> ul2 = importUserExcel(fileUrl, type);
			for (User u : ul2) {
				us.save(u);
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
	public String myList() throws Exception {
		// List<User> userList=us.findAll();
		// ActionContext.getContext().put("userList", userList);

		List<Department> topList = ds.findTopList();
		List<Department> departmentList = DepartmentUtils
				.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		if (departmentId == 0) {
			new QueryHelper(User.class, "u").preparePageBean(us, pageNum,
					pageSize);
		} else {
			Department department = ds.getById(departmentId);
			System.out.println("--->>>" + pageNum + "," + pageSize);
			System.out.println("===" + department.getName());
			if (department.getParent() == null) {
				new QueryHelper(User.class, "u").preparePageBean(us, pageNum,
						pageSize);
			} else {
				if (department.getChildren() != null
						&& department.getParent() != null) {
					new QueryHelper(User.class, "u")
							.addCondition("u.department.parent.id=? ",
									departmentId)
							.addConditionOther(true, "u.department.id= ?",
									departmentId)
							.preparePageBean(us, pageNum, pageSize);
				}
				else
				{
					 if (department.getParent().getParent() != null) {
						 System.out.println("----------->>>>"
						 + department.getParent().getId());
						 new QueryHelper(User.class, "u").addCondition(
						 "u.department.id=? ", departmentId).preparePageBean(us,
						 pageNum, pageSize);
						 }
				}
			}

		}

		return "list";
	}
	
	
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		us.delete(model.getId());
		return "toList";
	}
	
	public String addUI() throws Exception {
		// TODO Auto-generated method stub部门列表
		List<Department> topList=ds.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		//岗位列表
		List<Role> roleList=rs.findAll();
		ActionContext.getContext().put("roleList", roleList);
		
		return "saveUI";
	}
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		//封装到对象	
		//添加到数据库
		model.setDepartment(ds.getById(departmentId));
		List<Role> roleList=rs.getByIds(roleIds);
		model.setRoles(new HashSet<Role>(roleList));
		String md5Digest=DigestUtils.md5Hex("1234");
		model.setPassword(md5Digest);
		us.save(model);
		return "toList";
	}
	
	public String editUI() throws Exception {

		// TODO Auto-generated method stub部门列表
				List<Department> topList=ds.findTopList();
				List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
				ActionContext.getContext().put("departmentList", departmentList);
				//岗位列表
				List<Role> roleList=rs.findAll();
				ActionContext.getContext().put("roleList", roleList);
				
				User user=us.getById(model.getId());
				ActionContext.getContext().getValueStack().push(user);
				if(user.getDepartment()!=null)
				{
					departmentId=user.getDepartment().getId();
				}
				if(user.getRoles()!=null)
				{
					roleIds=new Long[user.getRoles().size()];
					int index=0;
					for(Role role:user.getRoles())
					{
						roleIds[index++]=role.getId();
					}
				}
				
		return "saveUI";
	}
	
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		User user=us.getById(model.getId());
		user.setDescription(model.getDescription());
		user.setEmail(model.getEmail());
		user.setGender(model.getGender());
		user.setLoginName(model.getLoginName());
		user.setName(model.getName());
		user.setPhoneNumber(model.getPhoneNumber());
		
		
		user.setDepartment(ds.getById(departmentId));
		List<Role> roleList=rs.getByIds(roleIds);
		user.setRoles(new HashSet<Role>(roleList));
		
		us.update(user);
		return "toList";
	}
	
	
	public String initPassword() throws Exception {
		// TODO Auto-generated method stub
		
		User user=us.getById(model.getId());
		
		String md5Digest=DigestUtils.md5Hex("1234");
		user.setPassword(md5Digest);
		
		us.update(user);
		return "toList";
	}
	public String loginUI() throws Exception {
	
		
		return "loginUI";
	}
	
	public String editMy() throws Exception {

		System.out.println(">>>>>>>>>>>>==================================<<<<<<<<<<<<<<<<<");
		System.out.println(model.getId());
		return "editMyUI";
	}
	
	public String editMyPass() throws Exception {
		
		//System.out.println("==================================<<<<<<<<<<<<<<<<<");
		
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		User userCheck=us.findByLoginNameAndPassword(user.getLoginName(),oldPassword);
	
		if(userCheck==null)
		{
			addFieldError("login", "密码错误！");
			return "editMyUI";
		}else
		{
			System.out.println(model.getPassword());
			String md5Digest=DigestUtils.md5Hex(model.getPassword());
			
			userCheck.setPassword(md5Digest);
			us.save(userCheck);
			return "successUI";
		}
	}
//个人信息显示
	public String myInfo() throws Exception {
		
		List<Department> topList=ds.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		

		if(user.getDepartment()!=null)
		{
			departmentId=user.getDepartment().getId();
			Department department=ds.getById(departmentId);
			ActionContext.getContext().put("dept", department.getName());
		}
		
		
		ActionContext.getContext().getValueStack().push(user);
	//	Department department=ds.getById(user.getDepartment().getId());
		
		//System.out.println(user.getDepartment().getId());
	//	ActionContext.getContext().getValueStack().push(department);
		return "myInfoUI";
	}
	
	//个人信息显示
		public String editMyInfo() throws Exception {
			User user=us.getById(model.getId());
			System.out.println("==================================<<<<<<<<<<<<<<<<<");
			
			System.out.println(model.getId());
			user.setDescription(model.getDescription());
			user.setEmail(model.getEmail());
			user.setGender(model.getGender());
			//user.setLoginName(model.getLoginName());
			user.setName(model.getName());
			user.setPhoneNumber(model.getPhoneNumber());
			System.out.println(model.getPhoneNumber());
			System.out.println(model.getDescription());

			us.update(user);
			return "successUI";
		}
	public String login() throws Exception {
		// TODO Auto-generated method stub
		
		User user=us.findByLoginNameAndPassword(model.getLoginName(),model.getPassword());
		
		if(user==null)
		{
			addFieldError("login", "用户名或者密码错误！");
			return "loginUI";
		}else
		{
			ActionContext.getContext().getSession().put("user", user);
			return "toIndex";
		}
		
	}
	
	public String logout() throws Exception {
		// TODO Auto-generated method stub
		
		ActionContext.getContext().getSession().remove("user");
		return "logout";
	}

	public String getOldPassword() {
		return oldPassword;
	}


	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}


	public Long[] getRoleIds() {
		return roleIds;
	}


	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}


	public Long getDepartmentId() {
		return departmentId;
	}


	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
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

	
	
}
