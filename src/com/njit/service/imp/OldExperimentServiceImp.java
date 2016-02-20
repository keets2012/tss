package com.njit.service.imp;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Experiment;
import com.njit.domain.OldExperiment;
import com.njit.domain.User;
import com.njit.service.ExperimentService;
import com.njit.service.OldExperimentService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class OldExperimentServiceImp extends DaoSupportImp<OldExperiment> implements OldExperimentService{

	
}
