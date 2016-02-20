package com.njit.view.action;

import java.util.*;


import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.njit.base.BaseAction;
import com.njit.domain.Forum;
import com.njit.domain.PageBean;
import com.njit.domain.Reply;
import com.njit.domain.Topic;
import com.njit.domain.User;
import com.njit.util.QueryHelper;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class TopicAction extends BaseAction<Topic>{

	private Long forumId;

	public String show() throws Exception {
		Topic topic=ts.getById(model.getId());
		ActionContext.getContext().put("topic", topic);
		
//	List<Reply> replyList=replyService.findByTopic(topic);
//		ActionContext.getContext().put("replyList", replyList);
		// 准备分页信息, 最终版
		new QueryHelper(Reply.class, "r")//
				.addCondition("r.topic=?", topic)//
				.addOrderProperty("r.postTime", true)//
				.preparePageBean(replyService, pageNum, pageSize);
		
		return "show";
	}
	
	public String addUI() throws Exception {
		Forum forum=fs.getById(forumId);
		ActionContext.getContext().put("forum", forum);
		return "addUI";
	}
	
	public String add() throws Exception {
		// 封装
		// >> 表单参数，已经封装了title, content
		// model.setTitle(title);
		// model.setContent(content);
		model.setForum(fs.getById(forumId));
		// >> 当前直接获取的信息
		model.setAuthor(getCurrentUser()); // 当前登录用户
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr()); // 当前请求中的IP
		model.setPostTime(new Date()); // 当前时间

		// 保存
		ts.save(model);

		return "toShow"; // 转到新主题的显示页面
	}
	
	public String edit() throws Exception {
		
	//	System.out.println(model.getId());
		Topic topic=ts.getById(model.getId());
		System.out.println(model.getType());
		topic.setType(model.getType());
		ts.saveCom(topic);
		
		return "toShow";
	}
	
	
	public String moveUI() throws Exception {
		List<Forum> forums = fs.findAll() ;
		Topic topic = ts.getById(model.getId()) ;
		ActionContext.getContext().put("forums", forums);
		ActionContext.getContext().put("topic", topic);
		return "moveUI";
	}
	
	
	public String move() throws Exception {
		Topic topic = ts.getById(model.getId()) ;
		System.out.println(model.getId());
		Forum newforum = fs.getById(forumId) ;
		
		System.out.println("===============================>>>>>>>>>>>");
		System.out.println(forumId);
		
		topic.setForum(newforum) ;
		ts.saveTurn(topic);
		return "toShow";
	}
	
//========================================
	public Long getForumId() {
		return forumId;
	}

	public void setForumId(Long forumId) {
		this.forumId = forumId;
	}
	
}
