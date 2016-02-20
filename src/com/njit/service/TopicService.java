package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Forum;
import com.njit.domain.PageBean;
import com.njit.domain.Topic;
import com.njit.domain.User;

public interface TopicService extends DaoSupport<Topic>{

	List<Topic> findByForum(Forum forum);

	PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum);

	void saveCom(Topic model);

	void saveTurn(Topic topic);



}
