package com.dengchao.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class Cat implements InitializingBean, DisposableBean {

	public Cat() {
		System.out.println("Construct Cat");
	}

	/**
	 * 容器关闭是调用
	 */
	@Override
	public void destroy() throws Exception {
		System.out.println("DisposableBean cat");

	}

	/**
	 * 对象创建，属性填充完成后调用
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("InitializingBean cat");
	}
}
