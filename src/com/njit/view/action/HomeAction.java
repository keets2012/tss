package com.njit.view.action;


import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.njit.base.BaseAction;
import com.njit.domain.Privilege;
import com.njit.domain.Role;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Scope("prototype")
public class HomeAction extends ActionSupport{


	public String index() throws Exception {
		// TODO Auto-generated method stub
		return "index";
	}
	
	
	public String top() throws Exception {
		// TODO Auto-generated method stub
		return "top";
	}
	
	public String bottom() throws Exception {
		// TODO Auto-generated method stub
		return "bottom";
	}
	
	
	public String left() throws Exception {
		// TODO Auto-generated method stub
		return "left";
	}
	
	
	public String right() throws Exception {
		// TODO Auto-generated method stub
		return "right";
	}
	
}
