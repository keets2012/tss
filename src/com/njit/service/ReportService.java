package com.njit.service;

import java.util.List;

import com.njit.base.DaoSupport;
import com.njit.domain.Report;
import com.njit.domain.User;

public interface ReportService extends DaoSupport<Report>{

	List<Report> findByUser();

	List<Report> findByTeacher();

	int querySub(Long departmentId, Long taskId);

	int queryOnTimeSub(Long departmentId, Long taskId);

	List<User> querySubUser(Long departmentId, Long taskId);

	List<User> queryOutDate(List<Report> subReportList);

	List<Report> findSub(Long departmentId, Long taskId);




}
