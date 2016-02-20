package com.njit.service.imp;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.njit.base.DaoSupportImp;
import com.njit.domain.Course;
import com.njit.domain.Curriculum;
import com.njit.domain.Department;
import com.njit.domain.Experiment;
import com.njit.domain.OldCourse;
import com.njit.domain.OldExperiment;
import com.njit.domain.User;
import com.njit.service.CourseService;
import com.njit.service.OldCourseService;
import com.njit.service.OldExperimentService;
import com.opensymphony.xwork2.ActionContext;

@SuppressWarnings("unchecked")
@Service
@Transactional
public class CourseServiceImp extends DaoSupportImp<Course> implements CourseService{


	@Resource
	private OldCourseService ocs;
	@Resource
	private OldExperimentService oes;
	@Override
	public List<Course> findByUser() {
		// TODO Auto-generated method stub
		User user = (User) ActionContext.getContext().getSession().get("user") ;
		return getSession().createQuery("select u.courses from User u where u.id=? ").setParameter(0, user.getId()).list();
	}

	@Override
	public List<Department> findByCourse(Long courseId) {
		// TODO Auto-generated method stub
		return getSession().createQuery("select depts from Course c where c.id=? ")
				.setParameter(0, courseId)
				.list();
	}

	@Override
	public List<String> findAllTerm() {
		//List<String> termList=new ArrayList<String>();
		
		return getSession().createQuery("select distinct term from Course ").list();
	}

	@Override
	public void deleteAll(List<Course> courseList, List<Experiment> expList) {
//		getSession().beginTransaction();

		try {
			for(Course course:courseList)
			{
				//System.out.println("==========="+courseList.size());
				OldCourse oldcourse=new OldCourse();
				oldcourse.setCourseNo(course.getCurriculum().getCourseNo());
				oldcourse.setCredit(course.getCurriculum().getCredit());

				String deptNames=""; 
				for(Department dept:course.getDepts())
				{
					deptNames+=dept.getName()+" ";
				}
				oldcourse.setDeptNames(deptNames);
				oldcourse.setDescription(course.getDescription());
				oldcourse.setName(course.getCurriculum().getName());
				oldcourse.setTerm(course.getTerm());
				oldcourse.setUserName(course.getUser().getName());				
				ocs.save(oldcourse);
			}
			for(Experiment exp:expList)
			{
				OldExperiment old=new OldExperiment();
				old.setCourseName(exp.getProject().getCurriculum().getName());
				old.setDeptName(exp.getDept().getName());
				old.setDescription(exp.getDescription());
				old.setExpId(exp.getExpId());
				old.setExpTerm(exp.getExpTerm());
				old.setExpTime(exp.getExpTime());
				old.setLabName(exp.getLab().getName());
				old.setProjectName(exp.getProject().getName());
				old.setUserName(exp.getUser().getName());
				oes.save(old);					
			}
			getSession().createQuery("delete from Report")
				.executeUpdate();
			getSession().createQuery("delete from Share").executeUpdate();
			getSession().createSQLQuery("delete from njit_department_task")
			.executeUpdate();
			getSession().createQuery("delete from Task").executeUpdate();
			getSession().createQuery("delete from Experiment").executeUpdate();
			getSession().createSQLQuery("delete from njit_department_course")
			.executeUpdate();
			getSession().createQuery("delete from Course").executeUpdate();
			
//			getSession().getTransaction().commit();		

		} catch (Exception e) {
			e.printStackTrace();
			getSession().getTransaction().rollback();
		}
	}

	@Override
	public Course getByName(String courseName, List<Course> courseList) {
		Course find=new Course();
		System.out.println("====dept:"+courseName);
		System.out.println("================长度为"+courseList.size());
		for(Course all:courseList)
		{
			System.out.println(all.getName());
			if(all.getName().equals(courseName))
			{
				find=all;
				break;
			}
		}
		return find;
	}


}
