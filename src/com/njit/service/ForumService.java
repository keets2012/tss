package com.njit.service;

import com.njit.base.DaoSupport;
import com.njit.domain.Forum;

public interface ForumService extends DaoSupport<Forum>{

	void moveUp(Long id);

	void moveDown(Long id);

}
