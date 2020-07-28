package com.dengchao.entity;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class Person {
	private String name;

	public Person(String name) {
		this.name = name;
		System.out.println("Construct Person -> " + name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person{" +
				"name='" + this.name + '\'' +
				'}';
	}
}
