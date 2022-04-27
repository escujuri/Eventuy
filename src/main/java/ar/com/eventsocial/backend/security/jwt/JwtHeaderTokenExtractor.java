package ar.com.eventsocial.backend.security.jwt;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JwtHeaderTokenExtractor implements ITokenExtractor {
    public static String HEADER_PREFIX = "Bearer ";

    @Autowired
	private JwtTokenUtil jwtTokenUtil;

    @Override
    public String Extract(String header) {
        if (StringUtils.isBlank(header)) {
            throw new AuthenticationServiceException("Authorization head	er cannot be blank!");
        }

        if (header.length() < HEADER_PREFIX.length()) {
            throw new AuthenticationServiceException("Invalid authorization header size.");
        }

        return header.substring(HEADER_PREFIX.length(), header.length());
    }
    
    public Map<String, Object> ReadToken(String token) throws Exception {
		
		if (jwtTokenUtil.isTokenExpired(token)) {
			throw new JwtExpiredTokenException(token);
		}
		Map<String, Object> claims = new HashMap<>();	
		claims = jwtTokenUtil.getMapFromTokenClaims(token);
		return claims;
	}

    
}
