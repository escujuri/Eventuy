package ar.com.eventsocial.backend.security.jwt;

import java.util.Map;

public interface ITokenExtractor {

	String Extract(String payload);
    
	Map<String, Object> ReadToken(String token) throws Exception;

}
