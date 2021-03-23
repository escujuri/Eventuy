package ar.com.eventsocial.backend.security.jwt;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import ar.com.eventsocial.backend.logs.LogMaker;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenUtil implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtil.class);

	public static final LogMaker log = new LogMaker();

	private static final long serialVersionUID = -2550185165626007488L;
	
	public static final long JWT_TOKEN_VALIDITY = 5*60*60;

	// Signing key for HS512 algorithm
    public static final String JWT_SECRET = "e5c9ee274ae87bc031adda32e27fa98b9290da90";

    // JWT token defaults
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "secure-api";
    public static final String TOKEN_AUDIENCE = "secure-app";

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";


	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	public Date getIssuedAtDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getIssuedAt);
	}

	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	private Claims getAllClaimsFromToken(String token) {
		//return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
		return Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token).getBody();
	}

	public Map<String, Object> getMapFromTokenClaims(String token) {
		Map<String, Object> expectedMap = new HashMap<String, Object>();
		if (tryParseJwtToken(token))
		{
			final Claims claims = getAllClaimsFromToken(token);
					
		    for(Entry<String, Object> entry : claims.entrySet()) {
		        expectedMap.put(entry.getKey() , entry.getValue());
		    }
		}
	    return expectedMap;
	}
	
	public Boolean isTokenSigned(String token) {
		return Jwts.parser().isSigned(token);
	}

	public Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(UserDetails userDetails, Map<String, Object> claims) {
		return doGenerateToken(claims, userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) 
	{
		String key = JWT_SECRET; // JWT_SECRET;
		return Jwts.builder()
			    .setId(UUID.randomUUID().toString())
				.setClaims(claims)
				.setSubject(subject)
				.setHeaderParam("typ", TOKEN_TYPE)
		        .setIssuer(TOKEN_ISSUER)
		        .setAudience(TOKEN_AUDIENCE)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setNotBefore(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*120000))
				.signWith(SignatureAlgorithm.HS512, key)
				.compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) 
				&& !isTokenExpired(token) && isTokenSigned(token));
	}
	
	public boolean tryParseJwtToken(String authToken) {
		try {
			String key = JWT_SECRET; // JWT_SECRET;

			Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}

		return false;
	}    
}