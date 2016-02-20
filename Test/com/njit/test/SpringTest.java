package com.njit.test;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.njit.service.CourseService;

public class SpringTest {

	private ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");

	@Test
	public void testBean() throws Exception {
		testAction testAction = (testAction) ac.getBean("testAction");
		System.out.println(testAction);
	}

	// 测试SessionFactory
	@Test
	public void testSessionFactory() throws Exception {
		SessionFactory sessionFactory = (SessionFactory) ac.getBean("sessionFactory");
		System.out.println(sessionFactory);
	}
	
	
	// 测试事务
	@Test
	public void testTransaction() throws Exception {

	}


}
