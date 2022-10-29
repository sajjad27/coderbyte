package com.coderbyte.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.coderbyte.auth.model.JwtClaims;
import com.coderbyte.auth.model.dao.AuthenticationRequest;
import com.coderbyte.auth.model.dao.AuthenticationResponse;
import com.coderbyte.auth.model.dao.RefreshTokenRequest;
import com.coderbyte.auth.model.entity.User;
import com.coderbyte.shared.exception.CoderbyteException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class AuthService {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtUtil;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder bcryptEncoder;


	public AuthenticationResponse getAuthenticationResponse(AuthenticationRequest authenticationRequest) {
		// authenticationManager.authenticate will call loadUserByUsername in
		// CustomUserDetailsService
		// and will do all the validation required
		// will then fill user object inside jwtClaims object
		authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
				authenticationRequest.getPassword()));
		return getAuthenticationResponse();
	}

	private AuthenticationResponse getAuthenticationResponse() {
		User loadedUser = this.customUserDetailsService.getLoadedUser();
		JwtClaims claims = this.jwtUtil.mapToJwtClaims(loadedUser);
		return getAuthenticationResponse(claims);
	}

	public AuthenticationResponse getAuthenticationResponse(RefreshTokenRequest refreshTokenRequest) {
		JwtClaims jwtClaims = null;
		Claims claims = null;
		try {
			claims = this.jwtUtil.getClaimsFromToken(refreshTokenRequest.getExpiredAccessToken());
			if (this.jwtUtil.isRefreshToken(claims)) {
				throw new CoderbyteException("PROVIDED_REFRESH_TOKEN", "expiredAccessToken");
			}
			jwtClaims = this.jwtUtil.map(claims);
			return this.getAuthenticationResponse(jwtClaims);
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException ex) {
			throw new CoderbyteException("FAKE_TOKEN", "expiredAccessToken");
		} catch (IllegalArgumentException ex) {
			throw new CoderbyteException("MISSING_EXPIRED_ACCESS_JWT", "expiredAccessToken");
		} catch (ExpiredJwtException e) {
			claims = e.getClaims();
			if (this.jwtUtil.isRefreshToken(claims)) {
				throw new CoderbyteException("PROVIDED_REFRESH_TOKEN", "expiredAccessToken");
			}
			jwtClaims = this.jwtUtil.map(claims);
			return this.getAuthenticationResponse(jwtClaims);
		}
	}

	public AuthenticationResponse getAuthenticationResponse(JwtClaims jwtClaims) {
		String token = jwtUtil.generateAccessToken(jwtClaims);
		String refreshToken = jwtUtil.generateRefreshToken(jwtClaims.getUserId());
		return new AuthenticationResponse(token, refreshToken);
	}


}
