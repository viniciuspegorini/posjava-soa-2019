package br.edu.utfpr.pb.posjava3auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import br.edu.utfpr.pb.posjava2common.security.JwtConfig;

@SpringBootApplication
@EnableEurekaClient
public class Posjava3AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava3AuthApplication.class, args);
	}
	
	@Bean
	public JwtConfig jwtConfig() {
		return new JwtConfig();
	}
	
}
