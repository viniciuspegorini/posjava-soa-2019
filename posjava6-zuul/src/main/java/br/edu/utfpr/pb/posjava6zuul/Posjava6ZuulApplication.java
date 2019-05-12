package br.edu.utfpr.pb.posjava6zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import br.edu.utfpr.pb.posjava2common.security.JwtConfig;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class Posjava6ZuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava6ZuulApplication.class, args);
	}
	
	@Bean
	public JwtConfig jwtConfi() {
		return new JwtConfig();
	}
	
}
