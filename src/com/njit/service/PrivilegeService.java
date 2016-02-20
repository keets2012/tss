package com.njit.service;


import java.util.Collection;
import java.util.List;

import com.njit.base.*;
import com.njit.domain.Privilege;

public interface PrivilegeService extends DaoSupport<Privilege>{

	List<Privilege> findTopList();

	Collection<String> getAllPrivilegeUrls();

}
