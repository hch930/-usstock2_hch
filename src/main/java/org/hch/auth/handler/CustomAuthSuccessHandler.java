package org.hch.auth.handler;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hch.auth.eo.ERole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler{
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	private final String DEFAULT_LOGIN_SUCCESS_URL = "/home";
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		clearAuthenticationAttributes(request);
		redirectStrategy(request, response, authentication);
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if(session != null) {
			session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
	}
	
	
	private void redirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
	throws IOException, ServletException{
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		if(savedRequest == null) {
			redirectStrategy.sendRedirect(request, response, DEFAULT_LOGIN_SUCCESS_URL);
		}else {
			Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
			if(roles.contains(ERole.ADMIN.getValue())) {
				redirectStrategy.sendRedirect(request, response, "/home/admin");
			}
			else if(roles.contains(ERole.MANAGER.getValue())) {
				redirectStrategy.sendRedirect(request, response, "/home/user");
			}else {
				redirectStrategy.sendRedirect(request, response, "/home/guest");
			}
		}
	}
}
