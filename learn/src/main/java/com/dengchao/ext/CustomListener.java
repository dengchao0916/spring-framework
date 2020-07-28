package com.dengchao.ext;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author : dengchao
 * @date : 2020 07 28
 */
@Component
public class CustomListener {

	@EventListener(classes = {ApplicationEvent.class})
	public void listen(ApplicationEvent event){
		System.out.println("CustomListener -> " + event);
	}
}
