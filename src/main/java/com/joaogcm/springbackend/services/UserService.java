package com.joaogcm.springbackend.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.joaogcm.springbackend.security.UserSS;

public class UserService {
	
	public static UserSS userAuthenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch(Exception e) {
			return null;
		}
	}
}