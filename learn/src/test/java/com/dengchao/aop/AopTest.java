package com.dengchao.aop;

import com.dengchao.config.AopConfig;
import com.dengchao.service.LogService;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class AopTest {

	@Test
	public void context() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
		LogService logService = context.getBean(LogService.class);
		logService.print();
	}
}
