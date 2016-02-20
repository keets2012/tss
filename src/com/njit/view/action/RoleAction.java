package com.njit.view.action;


import java.util.HashSet;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;


import com.njit.base.BaseAction;
import com.njit.domain.Privilege;
import com.njit.domain.Role;
import com.opensymphony.xwork2.ActionContext;

@Controller
@Scope("prototype")
public class RoleAction extends BaseAction<Role>{

	

	private Long[] privilegeIds;

	
	
	public Long[] getPrivilegeIds() {
		return privilegeIds;
	}


	public void setPrivilegeIds(Long[] privilegeIds) {
		this.privilegeIds = privilegeIds;
	}


	public String list() throws Exception {
		// TODO Auto-generated method stub
		List<Role> roleList=rs.findAll();
		ActionContext.getContext().put("roleList", roleList);
		return "list";
	}
	
	
	public String delete() throws Exception {
		// TODO Auto-generated method stub
		rs.delete(model.getId());
		return "toList";
	}
	
	public String addUI() throws Exception {
		// TODO Auto-generated method stub
		return "saveUI";
	}
	
	public String add() throws Exception {
		// TODO Auto-generated method stub
		//封装到对象	
		//添加到数据库
		rs.save(model);
		return "toList";
	}
	
	public String editUI() throws Exception {
		// TODO Auto-generated method stub
		Role role=rs.getById(model.getId());
		
		ActionContext.getContext().getValueStack().push(role);
		return "saveUI";
	}
	
	public String edit() throws Exception {
		// TODO Auto-generated method stub
		Role role=rs.getById(model.getId());
		role.setName(model.getName());
		role.setDescription(model.getDescription());
		rs.update(role);

		return "toList";
	}
	
	
	public String setPrivilegeUI() throws Exception {
		// 准备回显的数据
		Role role = rs.getById(model.getId());
		ActionContext.getContext().getValueStack().push(role);

		if (role.getPrivileges() != null) {
			privilegeIds = new Long[role.getPrivileges().size()];
			int index = 0;
			for (Privilege priv : role.getPrivileges()) {
				privilegeIds[index++] = priv.getId();
			}
		}
		// 准备数据 privilegeList
		List<Privilege> privilegeList = ps.findAll();
		ActionContext.getContext().put("privilegeList", privilegeList); 

		return "setPrivilegeUI";
	}
	
	public String setPrivilege() throws Exception {
		// 1，从数据库中获取原对象
		Role role = rs.getById(model.getId());

		// 2，设置要修改的属性
		List<Privilege> privilegeList = ps.getByIds(privilegeIds);
		role.setPrivileges(new HashSet<Privilege>(privilegeList));

		// 3，更新到数据库
		rs.update(role);

		return "toList";
	}
}
