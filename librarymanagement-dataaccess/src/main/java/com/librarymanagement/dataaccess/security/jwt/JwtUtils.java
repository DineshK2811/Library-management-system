package com.librarymanagement.dataaccess.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.librarymanagement.dataaccess.security.service.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;



@Component
public class JwtUtils {
	private static final Logger log = LoggerFactory.getLogger(JwtUtils.class);
	@Value("${auth.token.jwtSecret}")
	private String jwtSecret;
	@Value("${auth.token.jwtExpirationMs}")
	private int jwtExpirationMs;
	
	public String generateToken(Authentication authentication) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		return Jwts.builder().setSubject(userDetailsImpl.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
				.signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
	}
	
	public String extractUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException se) {
			log.error("Invalid Jwt Signature: {}", se.getMessage());
		} catch (MalformedJwtException mje) {
			log.error("Invalid Jwt token: {}", mje.getMessage());
		} catch (ExpiredJwtException eje) {
			log.error("Jwt token is Expired: {}", eje.getMessage());
		} catch (UnsupportedJwtException e) {
			log.error("Jwt token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			log.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

}
