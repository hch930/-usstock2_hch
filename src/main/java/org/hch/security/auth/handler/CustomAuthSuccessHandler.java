package org.hch.security.auth.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import lombok.Data;

@Data
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {
	private Logger log = LoggerFactory.getLogger(this.getClass());
	private String defaultUrl;
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	private RedirectStrategy redirectStragtegy = new DefaultRedirectStrategy();

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		// 에러세션 지우기
		clearAuthenticationAttributes(request);
		// Redirect URL 작업.
		resultRedirectStrategy(request, response, authentication);
	}
	
	private void resultRedirectStrategy(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		if ( savedRequest != null ) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info( " GO !!! savedRequest.getRedirectUrl : " + targetUrl );
			redirectStragtegy.sendRedirect(request, response, targetUrl);
		}else {
			log.info( " GO !!! savedRequest.getRedirectUrl : " + defaultUrl );
			redirectStragtegy.sendRedirect(request, response, defaultUrl);
		}
	}
	
	private void clearAuthenticationAttributes(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		if( session == null ) return ;
		session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
	}
}
