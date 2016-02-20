package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Curriculum;

public interface CurriculumService extends DaoSupport<Curriculum>{

	Curriculum getByName(String name);

	Curriculum findByNo(String courseNo);



}
