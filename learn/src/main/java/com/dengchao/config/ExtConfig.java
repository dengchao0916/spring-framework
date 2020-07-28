package com.dengchao.config;

import com.dengchao.entity.Car;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author : dengchao
 * @date : 2020 07 28
 */
@Configuration
@EnableTransactionManagement
@ComponentScan("com.dengchao.ext")
public class ExtConfig {

	@Bean
	public Car car() {
		return new Car();
	}
}
