package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Experiment;
import com.njit.domain.Laboratory;

public interface LaboratoryService extends DaoSupport<Laboratory>{

	List<Laboratory> findAvailLab(List<Laboratory> allLabList, List<Experiment> experimentList, String expTime);



}
