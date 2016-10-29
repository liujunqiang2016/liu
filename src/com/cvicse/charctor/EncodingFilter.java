package com.cvicse.charctor;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		System.out.println("------------编码过滤器开始------------");  
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse res=(HttpServletResponse)response;
		req.setCharacterEncoding("GBk");
		Enumeration<String> param=req.getParameterNames();
		while(param.hasMoreElements()){
			String key=param.nextElement();
			String value=req.getParameter(key);			
			System.out.println("key: "+key);
			System.out.println("value: "+value);
		}
		res.setCharacterEncoding("UTF-8");
		System.out.println("------------编码过滤器结束------------");
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub       
	}

}
