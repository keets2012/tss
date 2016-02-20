package com.njit.view.action;

import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njit.base.BaseAction;
import com.njit.domain.Department;
import com.njit.domain.Forum;
import com.njit.util.DepartmentUtils;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class ForumManageAction extends BaseAction<Forum>{
	
	public String list() throws Exception {
		List<Forum> forumList=fs.findAll();
		ActionContext.getContext().put("forumList", forumList);
	
		return "list";
	}
	

	
	public String delete() throws Exception {
	
		fs.delete(model.getId());
		return "toList";
	}
	
	public String addUI() throws Exception {
		//准备树状部门列表

		
		
		return "saveUI";
	}
	
	public String add() throws Exception {
		//封装到对象	
		//添加到数据库
		fs.save(model);
		return "toList";
	}
	
	public String editUI() throws Exception {		
		
		Forum forum=fs.getById(model.getId());
		ActionContext.getContext().getValueStack().push(forum);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		//从数据库取出数据
		Forum forum=fs.getById(model.getId());
		//设置要修改的属性
		forum.setName(model.getName());
		forum.setDescription(model.getDescription());
		//更新到数据库
		fs.update(forum);
		
		return "toList";
	}

	public String moveUp() throws Exception {		
		fs.moveUp(model.getId());

		return "toList";
	}
	public String moveDown() throws Exception {		
		fs.moveDown(model.getId());
		return "toList";
	}

}
