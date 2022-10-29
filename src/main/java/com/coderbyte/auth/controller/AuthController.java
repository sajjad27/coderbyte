package com.coderbyte.auth.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.coderbyte.auth.model.dao.AuthenticationRequest;
import com.coderbyte.auth.model.dao.RefreshTokenRequest;
import com.coderbyte.auth.service.AuthService;

@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(authService.getAuthenticationResponse(authenticationRequest));
	}

	@RequestMapping(value = "/refreshtoken", method = RequestMethod.POST)
	public ResponseEntity<?> refreshtoken(@RequestBody RefreshTokenRequest refreshTokenRequest,
			HttpServletRequest request) throws Exception {
		return ResponseEntity.ok(this.authService.getAuthenticationResponse(refreshTokenRequest));

	}

}
