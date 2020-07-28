package com.dengchao.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.stereotype.Component;
import org.springframework.util.StringValueResolver;

/**
 * 实现xxxAware接口，将spring底层组件注入到自定义的组件中
 * 底层的xxxAware接口都是通过xxxAwareProcessor处理实现的
 *
 * @author : dengchao
 * @date : 2020 07 27
 */
@Component
public class CustomerAware implements EmbeddedValueResolverAware, BeanNameAware, ApplicationContextAware {

	private ApplicationContext applicationContext;

	@Override
	public void setEmbeddedValueResolver(StringValueResolver resolver) {
		String resolveStringValue = resolver.resolveStringValue("OS -> ${os.name}, 20 * 3 = #{20 * 3}");
		System.out.println("resolve string : " + resolveStringValue);
	}

	@Override
	public void setBeanName(String name) {
		System.out.println("current bean name : " + name);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println(applicationContext);
		this.applicationContext = applicationContext;
	}
}
