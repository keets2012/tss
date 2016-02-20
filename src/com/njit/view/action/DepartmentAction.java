package com.njit.view.action;

import com.njit.base.BaseAction;
import com.njit.domain.Department;
import com.njit.service.DepartmentService;
import com.njit.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

@Controller
@Scope("prototype")
public class DepartmentAction extends BaseAction<Department>{

	private Long parentId;
	
	public Long getParentId() {
		return parentId;
	}


	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}


	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<Department> departmentList=null;
		if(parentId==null){
			departmentList=ds.findTopList();
		}
		else{
		departmentList=ds.findChildren(parentId);
		Department parent=ds.getById(parentId);
		ActionContext.getContext().put("parent", parent);
		}
		ActionContext.getContext().put("departmentList", departmentList);
		return "list";
	}
	

	
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		ds.delete(model.getId());
		return "toList";
	}
	
	public String addUI() throws Exception {
		// TODO Auto-generated method stub准备树状部门列表
		List<Department> topList=ds.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		
		
		return "saveUI";
	}
	
	public String add() throws Exception {
		// 封装信息到对象中
		Department parent = ds.getById(parentId);
		model.setParent(parent);

		// 保存
		ds.save(model);

		return "toList";
	}
	public String editUI() throws Exception {
		// TODO Auto-generated method stub
		
		List<Department> topList=ds.findTopList();
		List<Department> departmentList=DepartmentUtils.getAllDepartments(topList);
		ActionContext.getContext().put("departmentList", departmentList);
		Department department=ds.getById(model.getId());
		ActionContext.getContext().getValueStack().push(department);
		if(department.getParent()!=null)
		{
			parentId=department.getParent().getId();
		}
		return "saveUI";
	}
	
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		//从数据库取出数据
		//设置要修改的属性
		//更新到数据库
		Department department=ds.getById(model.getId());
		department.setName(model.getName());
		department.setDescription(model.getDescription());
		department.setParent(ds.getById(parentId));
		ds.update(department);
		return "toList";
	}


}
