package com.dengchao.lifecycle;

import com.dengchao.config.LifeCycleConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class LifeCycleTest {

	@Test
	public void context(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
		System.out.println("Context construct");
		//context.getBean("car");
		context.close();
	}
}
