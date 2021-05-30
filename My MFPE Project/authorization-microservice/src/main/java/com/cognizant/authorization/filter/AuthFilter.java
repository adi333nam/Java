package com.cognizant.authorization.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.cognizant.authorization.service.AuthUserDetailsService;
import com.cognizant.authorization.util.JwtTokenUtil;



@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	JwtTokenUtil tokenUtil;

	@Autowired
	AuthUserDetailsService userDetailsService;

	@Override

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String username = null;
		if (authHeader != null) {
			username = tokenUtil.getUsernameFromToken(authHeader);
		}

		if (username != null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			boolean isValid=tokenUtil.validateToken(authHeader, userDetails);
			if (isValid) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());

				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authenticationToken);

			}

		}
		filterChain.doFilter(request, response);
	}

}