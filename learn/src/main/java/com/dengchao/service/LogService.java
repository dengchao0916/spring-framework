package com.dengchao.service;

import org.springframework.stereotype.Component;

/**
 * @author : dengchao
 * @date : 2020 07 27
 */
@Component
public class LogService {

	public LogService(){
		System.out.println("LogService construct");
	}

	public void print(){
		System.out.println("print logService");
	}
}
