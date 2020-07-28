package com.dengchao.config;

import com.dengchao.entity.Car;
import com.dengchao.entity.Cat;
import com.dengchao.entity.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
@Configuration
@ComponentScan("com.dengchao.processor")
public class LifeCycleConfig {

	/**
	 * init: 单实例，对象创建完成，并且属性填充完成后调用，多实例，在获取bean的时候调用
	 * destroy: 单实例，容器关闭的时候调用，多实例需要手动调用
	 */
	//@Scope("prototype")
	@Bean(initMethod = "init", destroyMethod = "destroy")
	public Car car() {
		return new Car();
	}

	@Bean
	public Cat cat() {
		return new Cat();
	}

	@Bean
	public Dog dog() {
		return new Dog();
	}
}
