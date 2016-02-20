package com.njit.view.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.njit.base.BaseAction;
import com.njit.domain.Reply;
import com.njit.domain.Topic;
import com.opensymphony.xwork2.ActionContext;
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{

	private Long topicId;
	private String title;
	private String content;
	
	public Long getTopicId() {
		return topicId;
	}

	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}

	//======================================
	
	
	public String addUI() throws Exception {
		Topic topic=ts.getById(topicId);
		ActionContext.getContext().put("topic", topic);
		return "addUI";
	}
	
	public String add() throws Exception {
		
		model.setTopic(ts.getById(topicId));
		
		model.setAuthor(getCurrentUser());
		model.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		model.setPostTime(new Date());
		replyService.save(model);
		return "toTopicShow";
	}
	
	public String addQuick() throws Exception {
		Reply reply=new Reply();
		reply.setContent(content);
		reply.setTitle(title);
		reply.setTopic(ts.getById(topicId));
		
		reply.setAuthor(getCurrentUser());
		reply.setIpAddr(ServletActionContext.getRequest().getRemoteAddr());
		reply.setPostTime(new Date());
		replyService.save(reply);
		return "toTopicShow";
	}
}
