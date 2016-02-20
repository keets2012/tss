package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Experiment;
import com.njit.domain.Role;

public interface ExperimentService extends DaoSupport<Experiment>{

	List<Experiment> findByUser();

	List<Experiment> findByStudent();


}
