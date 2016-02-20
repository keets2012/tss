package com.njit.view.action;


import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.njit.base.BaseAction;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Privilege;
import com.njit.domain.Role;
import com.njit.domain.User;
import com.njit.service.CurriculumService;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class CurriculumAction extends BaseAction<Curriculum>{

	
	
	/** 列表 */
	public String list() throws Exception {
		List<Curriculum> curriculumList=curriculumService.findAll();
		ActionContext.getContext().put("curriculumList", curriculumList);
		
		return "list";
	}
	

	/** 删除 */
	public String delete() throws Exception {
		curriculumService.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		curriculumService.save(model);
		return "toList";
	}

	

	/** 修改页面 */
	public String editUI() throws Exception {
		Curriculum curriculum=curriculumService.getById(model.getId());
		ActionContext.getContext().getValueStack().push(curriculum);
		return "saveUI";
	}
	

	/** 修改 */
	public String edit() throws Exception {
		Curriculum curriculum=curriculumService.getById(model.getId());
		curriculum.setName(model.getName());
		curriculum.setCourseNo(model.getCourseNo());
		curriculum.setCredit(model.getCredit());
		curriculum.setDescription(model.getDescription());
		
		curriculumService.update(curriculum);
		
		
		return "toList";
	}

	
	
	
	
}
