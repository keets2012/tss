package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Share;

public interface ShareService extends DaoSupport<Share>{

	List<Share> findByUser();



}
