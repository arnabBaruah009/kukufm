package com.kuku.kuku.controller;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Check if the request is for the login page or login endpoint
        String uri = httpRequest.getRequestURI();
        System.out.println(uri);
        if (uri.endsWith("/register")) {
        	System.out.println("Register User");
            // Allow the request to proceed to the login page or endpoint
            chain.doFilter(request, response);
            return;
        }
        
        Boolean isLoggedIn = (Boolean) httpRequest.getSession().getAttribute("isLoggedIn");
        if (isLoggedIn != null && isLoggedIn) {
            // User is logged in, allow the request to proceed
            chain.doFilter(request, response);
        } else {
            // User is not logged in, redirect to the login page
            httpResponse.sendRedirect("/login");
        }
	}

}
