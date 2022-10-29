package com.coderbyte.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.coderbyte.auth.model.JwtClaims;
import com.coderbyte.auth.model.entity.User;
import com.coderbyte.shared.exception.CoderbyteException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Service
public class JwtService {

	private String secret;
	private int jwtExpirationInMs;
	private int refreshExpirationDateInMs;

	@Value("${jwt.secret}")
	public void setSecret(String secret) {
		this.secret = secret;
	}

	@Value("${jwt.expirationDateInMs}")
	public void setJwtExpirationInMs(int jwtExpirationInMs) {
		this.jwtExpirationInMs = jwtExpirationInMs;
	}

	@Value("${jwt.refreshExpirationDateInMs}")
	public void setRefreshExpirationDateInMs(int refreshExpirationDateInMs) {
		this.refreshExpirationDateInMs = refreshExpirationDateInMs;
	}

	public String generateAccessToken(JwtClaims jwtClaims) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("isRefreshToken", false);
		return doGenerateToken(claims, String.valueOf(jwtClaims.getUserId()), this.jwtExpirationInMs);
	}

	public String generateRefreshToken(Long userId) {
		Map<String, Object> claims = new HashMap<>();
		claims.put("isRefreshToken", true);
		return doGenerateToken(claims, String.valueOf(userId), this.refreshExpirationDateInMs);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject, int expirationInMs) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + expirationInMs))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public JwtClaims validateToken(String authToken, boolean isRefreshTokenRequest) throws CoderbyteException {
		try {
			JwtClaims jwtClaims = this.mapToJwtClaims(authToken);
			validateTokenCompatibility(authToken, isRefreshTokenRequest);
			return jwtClaims;
		} catch (SignatureException | MalformedJwtException | UnsupportedJwtException ex) {
			throw new CoderbyteException("FAKE_TOKEN", "Header");
		} catch (ExpiredJwtException ex) {
			throw new CoderbyteException("JWT_EXPIRED", "Header");
		} catch (IllegalArgumentException ex) {
			throw new CoderbyteException("MISSING_JWT", "Header");
		}
	}

	public JwtClaims mapToJwtClaims(String token) {
		// this method will validate the token then will return the jwtClaims
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return map(claims);
	}
	
	public Claims getClaimsFromToken(String token) {
		// this method will validate the token then will return the Claims
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	public JwtClaims map(Claims claims) {
		Long userId = Long.parseLong(claims.getSubject());
		return new JwtClaims(userId);
	}

	public JwtClaims mapToJwtClaims(User user) {
		JwtClaims jwtClaims = new JwtClaims();
		jwtClaims.setUserId(user.getId());
		return jwtClaims;
	}

	// TODO: this should be a unauthorized code
	private boolean validateTokenCompatibility(String jwtToken, boolean isRefreshTokenRequest) throws CoderbyteException {
		if (!isRefreshTokenRequest && this.isRefreshToken(jwtToken)) {
			throw new CoderbyteException("REFRESH_TOKEN_INCOMPATIBILITY", "refreshToken");
		} else if (isRefreshTokenRequest && !this.isRefreshToken(jwtToken)) {
			throw new CoderbyteException("ACCESS_TOKEN_INCOMPATIBILITY", "accessToken");
		}
		return isRefreshTokenRequest;
	}

	public String extractJwtFromRequest(HttpServletRequest request) {
		String bearerToken = request.getHeader("Authorization");
		if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		return null;
	}

	public boolean isRefreshToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return isRefreshToken(claims);
	}

	public boolean isRefreshToken(Claims claims) {
		Object isRefreshToken = claims.get("isRefreshToken");
		return isRefreshToken != null && (boolean) isRefreshToken;
	}

}
