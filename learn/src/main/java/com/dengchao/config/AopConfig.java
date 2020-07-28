package com.dengchao.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * AOP原理：
 * 1.{@link EnableAspectJAutoProxy}注解中，给容器导入 AspectJAutoProxyRegistrar,
 * 利用AspectJAutoProxyRegistrar自定义给容器注册bean,
 * bean的名字是internalAutoProxyCreator，类型是AnnotationAwareAspectJAutoProxyCreator
 *
 *
 *
 * 流程：
 * 1.传入配置类，创建IOC容器
 * 2.注册配置类，刷新容器(初始化容器)
 * 3.注册后置处理器registerBeanPostProcessors，用来拦截bean的创建
 * 	1）现获取IOC容器中已经定义好的，并且需要创建对象的所有后置处理器(都只是些定义，还未创建对象)
 * 	2）给容器中增加其他后置处理器，比如BeanPostProcessorChecker
 * 	3）优先注册实现了PriorityOrdered接口的BeanPostProcessor
 * 	4）再注册实现了Ordered接口的BeanPostProcessor
 * 	5）最后注册没有实现优先级接口的BeanPostProcessor
 * 	6）注册BeanPostProcessor，实际上就是创建BeanPostProcessor对象，然后保存在容器中
 * 		创建internalAutoProxyCreator【AnnotationAwareAspectJAutoProxyCreator】
 * 		1）创建bean实例
 * 		2）populateBean，给bean的各种赋值
 * 		3）initializeBean,初始化bean
 * 			1）invokeAwareMethods,处理aware接口的方法回调
 * 			2）applyBeanPostProcessorsBeforeInitialization，应用后置处理器的postProcessorsBeforeInitialization方法
 * 			3）invokeInitMethods执行自定义的初始化方法
 * 			4）applyBeanPostProcessorsAfterInitialization，应用后置处理器的postProcessorsAfterInitialization方法
 * 		4）BeanPostProcessor(AnnotationAwareAspectJAutoProxyCreator)创建成功，-> aspectJAdvisorBuilder
 * 	7）把BeanPostProcessor注册到BeanFactory中
 * 	   beanFactory.addBeanPostProcessor(postProcessor)
 * ===========以上是创建和注册后置处理器AnnotationAwareAspectJAutoProxyCreator的过程===========
 *
 *AnnotationAwareAspectJAutoProxyCreator是InstantiationAwareBeanPostProcessor类型的后置处理器
 * 4.finishBeanFactoryInitialization(beanFactory)完成BeanFactory初始化工作,创建剩下的单实例bean（业务逻辑组件和切面组件）
 * 	1）遍历获取容器中的bean，依次创建对象getBean(beanName)
 * 		getBean -> doGetBean -> getSingleton
 * 	2）创建Bean
 * 	   AnnotationAwareAspectJAutoProxyCreator会在创建bean之前，有一个拦截，会调用InstantiationAwareBeanPostProcessor的
 * 	   postProcessBeforeInstantiation方法
 * 		1）先从单例池中获取，如果能获取到，说明是之前创建过的，直接使用，否则创建
 * 		2）createBean，创建bean，AnnotationAwareAspectJAutoProxyCreator会在任何bean创建之前先尝试返回bean的实例
 * 			1）resolveBeforeInstantiation(beanName, mbdToUse);希望后置处理器在此能返回一个代理对象，如果能返回代理对象就使用，否则继续
 *				1）后置处理器先尝试返回对象，
 *					bean = applyBeanPostProcessorsBeforeInstantiation(targetType, beanName);
 *					拿到所有后置处理器，如果是InstantiationAwareBeanPostProcessor，就执行postProcessBeforeInstantiation方法
 * 					if (bean != null) {
 * 						bean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
 *                  }
 * 			2）doCreateBean(beanName, mbdToUse, args);真正的去创建一个bean实例，和3.6流程一样
 *
 * AnnotationAwareAspectJAutoProxyCreator【InstantiationAwareBeanPostProcessor】的作用：
 * 1.会在创建bean之前，调用postProcessBeforeInstantiation方法
 * 	1）判断当前bean是否在advisedBeans中（保存了所有需要增加的bean）
 * 	2）判断当前bean是否是基础类型的Advice、Pointcut、Advisor、AopInfrastructureBean或者是不是切面（是否有@Aspect注解）
 * 	3）是否需要跳过
 * 		1）获取候选的增强器（切面里面的通知方法）List<Advisor> candidateAdvisors
 * 		  每一个封装的通知方法的增强器是InstantiationModelAwarePointcutAdvisor
 * 		  判断每一个增强器是否是AspectJPointcutAdvisor类型的
 * 	    2）永远返回false
 * 2.创建对象，postProcessAfterInitialization，
 * 		return wrapIfNecessary(bean, beanName, cacheKey);//如果需要的情况下
 * 		1）获取当前bean的所有增强器（通知方法） specificInterceptors
 * 			1）找到候选的所有增强器（找到哪些通知方法是需要切入当前bean方法的）
 * 			2）获取到能在当前bean使用的增强器
 * 			3）给增强器排序
 * 		2）保存当前bean到advisedBeans中
 * 		3）如果当前bean需要增强，创建当前bean的代理对象
 * 			1）获取所有增强器
 * 			2）保存到proxyFactory
 * 			3）创建代理对象 spring自动觉得
 * 				1）JdkDynamicAopProxy(config); 如果这个类有实现接口
 * 				2）ObjenesisCglibAopProxy(config); 如果没有实现接口
 * 		4）给容器中返回当前组件使用cglib增强了的代理对象
 * 		5）以后容器中获取到的就是这个组件的代理对象，执行目标方法的时候，代理对象就会执行通知方法的流程
 * 3.目标方法执行
 * 	 容器中保存了组件的代理对象（cglib增强后的对象），这个对象里面保存了详细信息（比如增强器、目标对象）
 * 	 1）CglibAopProxy.intercept方法拦截目标方法的执行
 * 	 2）根据ProxyFactory对象获取将要执行的目标方法的拦截器链
 * 	   List<Object> chain = this.advised.getInterceptorsAndDynamicInterceptionAdvice(method, targetClass);
 * 	   	1）List<Object> interceptorLis保存所有拦截器，长度是一个默认的ExposeInvocationInterceptor加自己定义的增强器
 * 	   	2）遍历所有的增强器，将其转为Interceptor
 *			registry.getInterceptors(advisor);
 *		3）将增强器转为List<MethodInterceptor>
 *		 	如果是MethodInterceptor，直接加入集合中，如果不是，使用AdvisorAdapter将增强器转为MethodInterceptor，
 *		 	转换完成返回MethodInterceptor数组
 * 	 3）如果没有拦截器链，直接执行目标方法
 * 	 	拦截器链（每一个通知方法又被包装为方法拦截器，利用MethodInterceptor机制）
 * 	 4）如果有拦截器链，把需要执行的目标方法，目标对象拦截器链等信息传入一个CglibMethodInvocation对象并调用proceed方法
 *	 5）拦截器链的触发过程
 *	 	1）如果没有拦截器，直接执行目标方法，或者拦截器的索引和拦截器数组-1大小一样（指定到了最后一个拦截器）执行目标方法
 *	 	2）链式获取每一个拦截器，拦截器执行invoke方法，每一个拦截器等待下一个拦截器执行完成返回以后再来执行
 *	 	   拦截器链的机制，保证通知方法与目标方法的顺序执行
 *
 *总结：
 * 1.@EnableAspectJAutoProxy开启AOP功能
 * 2.@EnableAspectJAutoProxy会给容器注册一个组件AnnotationAwareAspectJAutoProxyCreator
 * 3.AnnotationAwareAspectJAutoProxyCreator是一个后置处理器
 * 4.容器创建流程：
 * 	1）registerBeanPostProcessors注册后置处理器，创建AnnotationAwareAspectJAutoProxyCreator
 * 	2）finishBeanFactoryInitialization初始化剩下的单实例bean
 * 		1）创建业务逻辑组件和切面组件
 * 		2）AnnotationAwareAspectJAutoProxyCreator拦截组件的创建过程
 * 		3）组件创建完成之后，判断组件是否需要增强
 * 			是：切面通知方法，包装成（Advisor）；给业务逻辑组件创建一个代理对象
 * 5.执行目标方法：
 * 	1）代理对象执行目标方法
 * 	2）CglibAopProxy.intercept
 * 		1）得到目标方法的拦截器链（增强器包装秤拦截器）MethodInterceptor
 * 		2）利用拦截器链机制，一次进入每一个拦截器进行执行
 * 		3）
 *
 * @author : dengchao
 * @date : 2020 07 19
 */

@EnableAspectJAutoProxy
@ComponentScan("com.dengchao.aop")
public class AopConfig {
}
