package com.dengchao.entity;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class Car {

	public Car() {
		System.out.println("Construct Car");
	}

	public void init() {
		System.out.println("init car");
	}

	public void destroy() {
		System.out.println("destroy car");
	}
}
