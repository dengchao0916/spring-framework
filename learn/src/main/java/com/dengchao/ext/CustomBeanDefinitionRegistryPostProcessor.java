package com.dengchao.ext;

import com.dengchao.entity.Yellow;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author : dengchao
 * @date : 2020 07 28
 */
@Component
public class CustomBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
	@Override
	public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
		System.out.println("CustomBeanDefinitionRegistryPostProcessor postProcessBeanDefinitionRegistry");
		System.out.println("CustomBeanDefinitionRegistryPostProcessor bean的数量 ->" + registry.getBeanDefinitionCount());

		AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Yellow.class).getBeanDefinition();
		registry.registerBeanDefinition("com.dengchao.entity.Yellow", beanDefinition);
	}

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("CustomBeanDefinitionRegistryPostProcessor postProcessBeanFactory");
		System.out.println("CustomBeanDefinitionRegistryPostProcessor bean的数量 ->" + beanFactory.getBeanDefinitionCount());
	}
}
