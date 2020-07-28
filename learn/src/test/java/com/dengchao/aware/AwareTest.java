package com.dengchao.aware;

import com.dengchao.config.AwareConfig;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class AwareTest {

	@Test
	public void context() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.register(AwareConfig.class);
		context.refresh();

		System.out.println("ApplicationContext : " + context);
	}
}
