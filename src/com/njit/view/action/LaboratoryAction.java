package com.njit.view.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
import com.njit.domain.Laboratory;
import com.njit.domain.Role;
import com.opensymphony.xwork2.ActionContext;


@Controller
@Scope("prototype")
public class LaboratoryAction extends BaseAction<Laboratory>{
	
	
	/** 列表 */
	public String list() throws Exception {
		List<Laboratory> laboratoryList=ls.findAll();
		ActionContext.getContext().put("laboratoryList", laboratoryList);
		return "list";
	}

	/** 删除 */
	public String delete() throws Exception {
		ls.delete(model.getId());
		return "toList";
	}

	/** 添加页面 */
	public String addUI() throws Exception {
		
		return "saveUI";
	}

	/** 添加 */
	public String add() throws Exception {
		
		ls.save(model);
		return "toList";
	}

	/** 修改页面 */
	public String editUI() throws Exception {
		
		Laboratory lab=ls.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(lab);
		
		return "saveUI";
	}

	/** 修改 */
	public String edit() throws Exception {
		
		Laboratory lab=ls.getById(model.getId());
		lab.setName(model.getName());
		
		lab.setAvail(model.getAvail());
		lab.setDescription(model.getDescription());
		ls.update(lab);

		
		return "toList";
	}

}
