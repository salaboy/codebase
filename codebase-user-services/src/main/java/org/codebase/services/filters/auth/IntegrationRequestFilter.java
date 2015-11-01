/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.codebase.services.filters.auth;

import com.nimbusds.jose.JOSEException;

import com.nimbusds.jwt.JWTClaimsSet;
import java.io.IOException;
import java.text.ParseException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.codebase.shared.util.CodebaseUtil;
import org.joda.time.DateTime;

/**
 *
 * @author salaboy
 */
public class IntegrationRequestFilter implements Filter{

    private static final String AUTH_ERROR_MSG = "Please make sure your request has an Authorization header",
								EXPIRE_ERROR_MSG = "Token has expired",
								JWT_ERROR_MSG = "Unable to parse JWT",
								JWT_INVALID_MSG = "Invalid JWT token"
	;
    public void init(FilterConfig filterConfig) throws ServletException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String authHeader = httpRequest.getHeader(CodebaseUtil.AUTH_HEADER_KEY);
		
		if (StringUtils.isBlank(authHeader) || authHeader.split(" ").length != 2) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, AUTH_ERROR_MSG);
		} else {
			JWTClaimsSet claimSet = null;
			try {
				claimSet = (JWTClaimsSet) CodebaseUtil.decodeToken(authHeader);
			} catch (ParseException e) {
				httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_ERROR_MSG);
				return;
			} catch (JOSEException e) {
				httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, JWT_INVALID_MSG);
				return;
			}


			// ensure that the token is not expired
			if (new DateTime(claimSet.getExpirationTime()).isBefore(DateTime.now())) {
				httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, EXPIRE_ERROR_MSG);
			} else {
				chain.doFilter(request, response);
			}
		}	
    }

    public void destroy() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

 
    
}
