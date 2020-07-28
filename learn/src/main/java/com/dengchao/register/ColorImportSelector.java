package com.dengchao.register;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 自定义逻辑返回需要注册的组件
 *
 * @author : dengchao
 * @date : 2020 07 27
 */
public class ColorImportSelector implements ImportSelector {
	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		System.out.println("ColorImportSelector ->" + importingClassMetadata.getClassName());
		return new String[]{"com.dengchao.entity.Red", "com.dengchao.entity.Yellow"};
	}
}
