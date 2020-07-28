package com.dengchao.config;

import com.dengchao.conditional.LinuxCondition;
import com.dengchao.conditional.MacCondition;
import com.dengchao.entity.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
@Configuration
public class ConditionalConfig {


	@Bean
	@Conditional({LinuxCondition.class})
	public Person person1() {
		return new Person("person1");
	}

	@Bean
	@Conditional({MacCondition.class})
	public Person person2() {
		return new Person("person2");
	}

	@Bean
	@Lazy
	public Person person3() {
		return new Person("person3");
	}
}
