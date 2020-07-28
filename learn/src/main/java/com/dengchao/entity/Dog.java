package com.dengchao.entity;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class Dog {

	public Dog(){
		System.out.println("Construct Dog");
	}

	@PostConstruct
	public void inti(){
		System.out.println("init dog");
	}

	@PreDestroy
	public void destroy(){
		System.out.println("destroy dog");
	}
}
