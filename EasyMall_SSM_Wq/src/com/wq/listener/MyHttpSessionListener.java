package com.wq.listener;

import java.util.HashMap;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.web.context.annotation.SessionScope;

import com.wq.domain.Product;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub
		//设置超时时间自动销毁
		se.getSession().setMaxInactiveInterval(240);
		se.getSession().setAttribute("cartmap", new HashMap<Product, Integer>());
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		// TODO Auto-generated method stub
	}

}
