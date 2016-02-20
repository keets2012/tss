package com.njit.service.imp;

import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Forum;
import com.njit.domain.PageBean;
import com.njit.domain.Topic;
import com.njit.domain.User;
import com.njit.service.TopicService;
import com.njit.service.UserService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class TopicServiceImp extends DaoSupportImp<Topic> implements TopicService{

	@Deprecated
	public List<Topic> findByForum(Forum forum) {
		return getSession().createQuery(//
				// 排序：所有置顶帖在最上面，并按最后更新时间排序，让新状态的在上面。
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.list();
	}

	@Override
	public void save(Topic topic) {
		// 1，设置属性并保存
		topic.setType(Topic.TYPE_NORMAL); // 默认为普通帖
		topic.setReplyCount(0);
		topic.setLastReply(null);   
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic); // 保存

		// 2，维护相关的特殊属性
		Forum forum = topic.getForum();
		forum.setTopicCount(forum.getTopicCount() + 1); // 主题数量
		forum.setArticleCount(forum.getArticleCount() + 1);// 文章数量（主题数+回复数）
		forum.setLastTopic(topic); // 最后发表的主题
		getSession().update(forum);
	}
	
	public void saveCom(Topic topic) {
		// 1，设置属性并保存
		//topic.setType(Topic.TYPE_NORMAL); // 默认为普通帖
//		topic.setReplyCount(0);
//		topic.setLastReply(null);   
//		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic); // 保存

	}
	
	
	public void saveTurn(Topic topic) {
		// 1，设置属性并保存
		getSession().save(topic);
		Forum forum=topic.getForum();
		
		forum.setArticleCount(forum.getArticleCount()+1);
		topic.setReplyCount(topic.getReplyCount()+topic.getReplyCount());
		topic.setLastReply(topic.getLastReply());
		topic.setLastUpdateTime(topic.getLastUpdateTime());
		
		
		getSession().update(forum);
		getSession().update(topic);
		
		
		
		
		
		topic.setReplyCount(0);
		topic.setLastReply(null);   
		topic.setLastUpdateTime(topic.getPostTime());
		getSession().save(topic); // 保存

	}

	public PageBean getPageBeanByForum(int pageNum, int pageSize, Forum forum) {

		// 查询本页的数据列表
		List list = getSession().createQuery(//
				"FROM Topic t WHERE t.forum=? ORDER BY (CASE t.type WHEN 2 THEN 2 ELSE 0 END) DESC, t.lastUpdateTime DESC")//
				.setParameter(0, forum)//
				.setFirstResult((pageNum - 1) * pageSize)//
				.setMaxResults(pageSize)//
				.list();

		// 查询总记录数量
		Long count = (Long) getSession().createQuery(//
				"SELECT COUNT(*) FROM Topic t WHERE t.forum=?")//
				.setParameter(0, forum)//
				.uniqueResult();

		return new PageBean(pageNum, pageSize, count.intValue(), list);
	}

	
}