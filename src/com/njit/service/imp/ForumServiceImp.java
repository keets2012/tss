package com.njit.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Forum;
import com.njit.service.ForumService;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class ForumServiceImp extends DaoSupportImp<Forum> implements
		ForumService {


	@Override
	public List<Forum> findAll() {
		// TODO Auto-generated method stub
		return getSession().createQuery("from Forum f order by f.position")
				.list();
	}

	@Override
	public void moveUp(Long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position<? order by f.position desc ")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();

		if (other == null) {
			return;
		}

		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到数据库

		getSession().update(forum);
		getSession().update(other);
	}


	@Override
	public void save(Forum forum) {
		// TODO Auto-generated method stub
		super.save(forum);
		forum.setPosition(forum.getId().intValue());
	}

	@Override
	public void moveDown(Long id) {
		Forum forum = getById(id);
		Forum other = (Forum) getSession()
				.createQuery(
						"from Forum f where f.position>? order by f.position asc ")
				.setParameter(0, forum.getPosition()).setFirstResult(0)
				.setMaxResults(1).uniqueResult();

		if (other == null) {
			return;
		}

		int temp = forum.getPosition();
		forum.setPosition(other.getPosition());
		other.setPosition(temp);
		// 更新到数据库

		getSession().update(forum);
		getSession().update(other);
	}

}
