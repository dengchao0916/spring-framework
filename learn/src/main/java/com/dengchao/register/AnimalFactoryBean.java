package com.dengchao.register;

import com.dengchao.entity.Animal;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
public class AnimalFactoryBean implements FactoryBean<Animal> {
	@Override
	public Animal getObject() throws Exception {
		return new Animal();
	}

	@Override
	public Class<?> getObjectType() {
		return Animal.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
