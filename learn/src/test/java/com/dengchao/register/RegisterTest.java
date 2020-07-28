package com.dengchao.register;

import com.dengchao.config.ExtConfig;
import com.dengchao.config.RegisterConfig;
import org.junit.Test;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class RegisterTest {


	@Test
	public void context() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RegisterConfig.class);
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("beanDefinitionName -> " + beanDefinitionName);
		}

		Object animal = context.getBean("animalFactoryBean");
		System.out.println(animal.getClass());

		Object beanFactory = context.getBean("&animalFactoryBean");
		System.out.println(beanFactory.getClass());
	}

	@Test
	public void context2() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ExtConfig.class);
		context.publishEvent(new ApplicationEvent(new String("some message")) {
		});
		context.close();
	}


}
