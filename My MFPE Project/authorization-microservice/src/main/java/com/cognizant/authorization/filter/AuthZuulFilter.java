package com.cognizant.authorization.filter;


import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class AuthZuulFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		log.info("Request Intercepted..");
		RequestContext context=RequestContext.getCurrentContext();
		HttpServletRequest request=context.getRequest();
		String user="tanmay";
		String header=request.getHeader("Authorization");
		if(header==null || header.isEmpty()||header.equals(user)) {
			return null;
		}
		
		context.setSendZuulResponse(false);
		context.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
		context.setResponseBody("Unauthorized user : "+header);
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
	}

}