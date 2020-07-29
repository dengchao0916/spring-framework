package com.dengchao.dependency;

import org.springframework.stereotype.Component;

/**
 *
 * @author : dengchao
 * @date : 2020 07 19
 */
@Component
public class UserService {

	/*@Autowired
	private IndexService indexService;*/

	public UserService(){
		System.out.println("UserService construct");
	}

	/*public IndexService getIndexService(){
		System.out.println("UserService getIndexService");
		return indexService;
	}*/
}
