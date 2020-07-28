package com.dengchao.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class LinuxCondition implements Condition {
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		Environment environment = context.getEnvironment();
		String os = environment.getProperty("os.name");
		if (os.contains("linux")){
			return true;
		}
		return false;
	}
}
