package com.cvicse.charctor;

import java.io.IOException;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class CharctorFilter implements Filter{

	@Override
	public void destroy() {
	
		 System.out.println("destroyed"); 
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		System.out.println("------Filter开始--------"); 
		HttpServletRequest request = (HttpServletRequest)req;
	    HttpServletResponse response =(HttpServletResponse)res;
	    MyRequest myRequest = new MyRequest(request);
	    System.out.println("------Filter结束--------");
	    chain.doFilter(myRequest, response);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		
	}
	
	class MyRequest extends HttpServletRequestWrapper{
        private HttpServletRequest request; 
        

        public MyRequest(HttpServletRequest request) {
            super(request);
            this.request = request;
        }
        @Override
        public String getParameter(String name) {
        	String value="abc";
        	String param=null;
              if(this.request.getQueryString()!=null){
            	  String querystr=this.request.getQueryString();
            	  param=querystr;
            	 if(!param.contains("&")){            //单个参数的情况
            		 String key=param.split("=")[0];
            		 if(key.equalsIgnoreCase(name)){
            		  value=param.split("=")[1];
            		  value=((EscapeToolBox.unescape(value)));
            		  return value;
            		 }
            	 }
            	 else{
            		  String[] paramArray=this.request.getQueryString().split("&");  //多个参数的情况
            	      for(int i=0;i<paramArray.length;i++){
            		  String valuemap=paramArray[i];
            		  String key=valuemap.split("=")[0];   
            		  if(key.equalsIgnoreCase(name)){
            		  value=valuemap.split("=")[1];
            		  value=((EscapeToolBox.unescape(value))); 
                      return value;
            		  }
            	    }
            	 }
              }
                          return value;
        }   
    }

}
