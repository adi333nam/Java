package com.cognizant.authorization.util;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

private static final long serialVersionUID = -2550185165626007488L;

/**
 * public static final long JWT_TOKEN_VALIDITY = 5 *60*60   ;
 */
public static final long JWT_TOKEN_VALIDITY = 2 * (long)60   ;
@Value("${jwt.secret}")
private String secret;


public String getUsernameFromToken(String token) {
	log.info("JWTUtil class getUsernameFromToken method called ");
return getClaimFromToken(token, Claims::getSubject);
}


public Date getExpirationDateFromToken(String token) {
	log.info("JWTUtil class getExpirationDateFromToken method called ");
return getClaimFromToken(token, Claims::getExpiration);
}

public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
final Claims claims = getAllClaimsFromToken(token);
log.info("JWTUtil class getClaimFromToken method called ");
return claimsResolver.apply(claims);
}
    
private Claims getAllClaimsFromToken(String token) {
	log.info("JWTUtil class getAllClaimsFromToken method called ");	
return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
}


private Boolean isTokenExpired(String token) {
final Date expiration = getExpirationDateFromToken(token);
log.info("JWTUtil class isTokenExpired method called ");	
return expiration.before(new Date());
}


public String generateToken(UserDetails userDetails) {
	log.info("JWTUtil class generateToken method called ");	
Map<String, Object> claims = new HashMap<>();
return doGenerateToken(claims, userDetails.getUsername());
}


private String doGenerateToken(Map<String, Object> claims, String subject) {
	log.info("JWTUtil class doGenerateToken method called ");	
return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
.signWith(SignatureAlgorithm.HS512, secret).compact();
}

public Boolean validateToken(String token, UserDetails userDetails) {
	log.info("JWTUtil class validateToken method called ");	
final String username = getUsernameFromToken(token);
return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));

}


public Boolean validateToken(String token) {
	
	log.info("JWTUtil class validateToken method called ");	
	return !isTokenExpired(token);
}

public String extractUsername(String token) {
	log.info("JWTUtil class extractUsername method called ");	
	String extractClaim = extractClaim(token, Claims::getSubject);
	log.debug("EXTRACT CLAIM {}:", extractClaim);
	return extractClaim;
}


public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
	log.info("JWTUtil class extractClaim method called ");
	final Claims claims = extractAllClaims(token);
	return claimsResolver.apply(claims);
}

private Claims extractAllClaims(String token) {

	log.info("JWTUtil class extractAllClaimsxtractClaim method called ");
	return  Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

	
}





}