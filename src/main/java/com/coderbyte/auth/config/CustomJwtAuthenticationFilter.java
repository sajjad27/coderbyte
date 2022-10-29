package com.coderbyte.auth.config;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.coderbyte.auth.model.JwtClaims;
import com.coderbyte.auth.service.JwtService;
import com.coderbyte.shared.exception.CoderbyteException;

@Component
public class CustomJwtAuthenticationFilter extends OncePerRequestFilter 
{

	@Autowired
	private JwtService jwtService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		try {
			boolean isRefreshTokenRequest = isRefreshTokenRequest(request);
			String jwtToken = jwtService.extractJwtFromRequest(request);
			JwtClaims jwtClaims = this.jwtService.validateToken(jwtToken, isRefreshTokenRequest);

			prepopulateUserData(request, jwtClaims);
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = getUsernamePasswordAuthenticationToken(
					jwtClaims, isRefreshTokenRequest);
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		} catch (CoderbyteException e) {
			request.setAttribute("CoderbyteException", e);
		}
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken(JwtClaims jwtClaims,
			boolean isRefreshTokenRequest) {
		if (!isRefreshTokenRequest) {
			UserDetails userDetails = getUserDetails(jwtClaims);
			return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		} else {
			return new UsernamePasswordAuthenticationToken(null, null, null);
		}
	}

	private UserDetails getUserDetails(JwtClaims jwtClaims) {
		UserDetails userDetails = new User(String.valueOf(jwtClaims.getUserId()), "",
				Arrays.asList(new SimpleGrantedAuthority("user")));
		return userDetails;
	}

	private boolean isRefreshTokenRequest(HttpServletRequest request) {
		return request.getRequestURL().toString().endsWith("refreshtoken");
	}

	private void prepopulateUserData(HttpServletRequest request, JwtClaims jwtClaims) throws CoderbyteException {
		request.setAttribute("jwtClaims", jwtClaims);
	}
}