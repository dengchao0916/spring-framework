package com.dengchao.dependency;

import com.dengchao.config.DependencyConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : dengchao
 * @date : 2020 07 29
 */
public class DependencyTest {

	@Test
	public void context(){
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DependencyConfig.class);
		context.close();
	}
}
