package com.dengchao.conditional;

import com.dengchao.config.ConditionalConfig;
import com.dengchao.entity.Person;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Map;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class ConditionalTest {
	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConditionalConfig.class);

	@Test
	public void context() {
		String[] beanDefinitionNames = context.getBeanDefinitionNames();
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("beanDefinitionName -> " + beanDefinitionName);
		}
		System.out.println("=====");

		String[] beanNamesForType = context.getBeanNamesForType(Person.class);
		for (String s : beanNamesForType) {
			System.out.println("person beanName -> " + s);
		}
		System.out.println("=====");

		Map<String, Person> persons = context.getBeansOfType(Person.class);
		System.out.println(persons);
	}
}
