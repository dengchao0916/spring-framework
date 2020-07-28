package com.dengchao.ext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * 在BeanFactory标准初始化后调用，所有bean定义已经保存到BeanFactory中，但是bean的实例还未创建
 *
 * @author : dengchao
 * @date : 2020 07 28
 */
@Component
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		System.out.println("CustomBeanFactoryPostProcessor...");
		int count = beanFactory.getBeanDefinitionCount();
		String[] names = beanFactory.getBeanDefinitionNames();
		System.out.println("当前BeanFactory中一共有" + count + "个bean");
		for (String name : names) {
			System.out.println(name);
		}
		System.out.println("============");
	}
}
