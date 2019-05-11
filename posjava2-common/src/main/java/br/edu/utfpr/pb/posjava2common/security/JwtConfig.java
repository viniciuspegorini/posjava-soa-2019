package br.edu.utfpr.pb.posjava2common.security;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class JwtConfig {

	@Value("${security.jwt.uri:/auth/**}")
	private String uri;
	
	@Value("${security.jwt.header:Authorization}")
	private String header;
	
	@Value("${security.jwt.prefix:Bearer }")
	private String prefix;
	
	@Value("${security.jwt.expiration:#{24*60*60}}")
	private long expiration;
	
	@Value("${security.jwt.secret:utfpr}")
	private String secret;
}
