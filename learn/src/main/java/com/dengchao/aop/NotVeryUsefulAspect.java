package com.dengchao.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : dengchao
 * @date : 2020 07 25
 */
@Component
@Aspect
public class NotVeryUsefulAspect {

	@Pointcut("execution(* com.dengchao.service.*.*(..))")
	public void anyPublicMethod() {

	}

	@Before("anyPublicMethod()")
	public void before() {
		System.out.println("===========before===========");
	}

	@After("anyPublicMethod()")
	public void after() {
		System.out.println("===========after===========");
	}
}
