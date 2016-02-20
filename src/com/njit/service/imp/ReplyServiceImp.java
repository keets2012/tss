package com.njit.service.imp;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Forum;
import com.njit.domain.PageBean;
import com.njit.domain.Reply;
import com.njit.domain.Topic;
import com.njit.domain.User;
import com.njit.service.ReplyService;
import com.njit.service.UserService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class ReplyServiceImp extends DaoSupportImp<Reply> implements ReplyService{


	@Override
	public List<Reply> findByTopic(Topic topic) {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Reply r where r.topic=? order by r.postTime").setParameter(0, topic).list();
	}

	@Override
	public void save(Reply reply) {
	
		getSession().save(reply);
		Topic topic=reply.getTopic();
		Forum forum=topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);
		topic.setReplyCount(topic.getReplyCount()+1);
		topic.setLastReply(reply);
		topic.setLastUpdateTime(reply.getPostTime());
		
		
		getSession().update(forum);
		getSession().update(topic);
		
	}

	@Override
	public PageBean getPageBeanByTopic(int pageNum, int pageSize, Topic topic) {

		List list=getSession().createQuery
				("from Reply r where r.topic=? order by r.postTime").setParameter(0, topic).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).list();
		Long count=(Long) getSession().createQuery("select count(*) from Reply r where r.topic=?").setParameter(0, topic).setFirstResult((pageNum-1)*pageSize).setMaxResults(pageSize).uniqueResult();
		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}



}
