package com.dengchao.dependency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author : dengchao
 * @date : 2020 07 19
 */
@Component
public class IndexService {

	@Autowired
	private UserService userService;

	public IndexService(){
		System.out.println("IndexService construct");
	}

	public UserService getUserService(){
		System.out.println("IndexService getUserService");
		return userService;
	}
}
