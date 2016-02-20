package com.njit.util;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.njit.domain.Privilege;
import com.njit.domain.User;

@Component
public class Installer {

	@Resource
	private SessionFactory sessionFactory;

	/**
	 * 执行安装
	 */
	@Transactional
	public void install() {
		Session session = sessionFactory.getCurrentSession();

		// ==============================================================
		// 保存超级管理员用户
	User user = new User();
		user.setLoginName("admin");
		user.setName("超级管理员");
		user.setPassword(DigestUtils.md5Hex("admin"));
		session.save(user); // 保存

		// ==============================================================
		// 保存权限数据
		Privilege menu, menu1, menu2, menu3, menu4, menu5,menu6;

		

		// --------------------
		menu = new Privilege("实验资源", null, null);
		menu1 = new Privilege("实验室", "/lab_list", menu);
		menu2 = new Privilege("实验课程", "/cur_list", menu);
		menu3 = new Privilege("课程分配", "/course_arrList", menu);
		menu4 = new Privilege("实验项目", "/project_list", menu);

		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		
		
		// --------------------
		menu = new Privilege("课程资源", null, null);
		menu1 = new Privilege("我的课程", "/course_myList", menu);
		menu2 = new Privilege("共享资料", "/share_list", menu);
		menu3 = new Privilege("我的共享", "/share_myList", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		
		
		// --------------------
		menu = new Privilege("我的实验", null, null);
		menu1 = new Privilege("预约实验", "/exp_queryLab", menu);
		menu2 = new Privilege("实验安排", "/exp_list", menu);
		menu3 = new Privilege("我的课表", "/exp_myCourse", menu);
		menu4 = new Privilege("查看课表", "/exp_course", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		
		
		// --------------------
		menu = new Privilege("实验报告", null, null);
		menu1 = new Privilege("下发任务", "/task_addUI", menu);
		menu2 = new Privilege("提交报告", "/report_myList", menu);
		menu3 = new Privilege("报告统计", "/report_count", menu);
		menu4 = new Privilege("我的报告", "/report_list", menu);
		menu5 = new Privilege("我的下发", "/task_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);
		session.save(menu4);
		session.save(menu5);
		
		// --------------------
		menu = new Privilege("CS Forum", null, null);
		menu1 = new Privilege("论坛管理", "/forumManage_list", menu);
		menu2 = new Privilege("论坛", "/forum_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		
		
		// --------------------
		menu = new Privilege("系统管理", null, null);
		menu1 = new Privilege("岗位管理", "/role_list", menu);
		menu2 = new Privilege("部门管理", "/department_list", menu);
		menu3 = new Privilege("用户管理", "/user_list", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
		session.save(menu3);

		session.save(new Privilege("岗位列表", "/role_list", menu1));
		session.save(new Privilege("岗位删除", "/role_delete", menu1));
		session.save(new Privilege("岗位添加", "/role_add", menu1));
		session.save(new Privilege("权限设置", "/role_setPrivilegeUI", menu1));
		session.save(new Privilege("岗位修改", "/role_edit", menu1));

		session.save(new Privilege("部门列表", "/dept_list", menu2));
		session.save(new Privilege("部门删除", "/dept_delete", menu2));
		session.save(new Privilege("部门添加", "/dept_add", menu2));
		session.save(new Privilege("部门修改", "/deptment_edit", menu2));

		session.save(new Privilege("用户列表", "/user_list", menu3));
		session.save(new Privilege("用户删除", "/user_delete", menu3));
		session.save(new Privilege("用户添加", "/user_add", menu3));
		session.save(new Privilege("用户修改", "/user_edit", menu3));
		session.save(new Privilege("初始化密码", "/user_initPassword", menu3));

		// --------------------		
		menu = new Privilege("个人设置", null, null);
		menu1 = new Privilege("个人信息", "/user_myInfo", menu);
		menu2 = new Privilege("密码修改", "/user_editMy", menu);
		session.save(menu);
		session.save(menu1);
		session.save(menu2);
						
	}

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		Installer installer = (Installer) ac.getBean("installer");
		installer.install();
	}
}
