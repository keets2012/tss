package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.PageBean;
import com.njit.domain.Reply;
import com.njit.domain.Topic;
import com.njit.domain.User;

public interface ReplyService extends DaoSupport<Reply>{

	List<Reply> findByTopic(Topic topic);

	PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic);
	




}
