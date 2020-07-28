package com.dengchao.config;

import com.dengchao.entity.User;
import com.dengchao.register.AnimalFactoryBean;
import com.dengchao.register.ColorImportSelector;
import com.dengchao.register.RainBowImportBeanDefinitionRegister;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
@Configuration
@Import({User.class, ColorImportSelector.class, RainBowImportBeanDefinitionRegister.class})
public class RegisterConfig {

	@Bean
	public AnimalFactoryBean animalFactoryBean() {
		return new AnimalFactoryBean();
	}

}
