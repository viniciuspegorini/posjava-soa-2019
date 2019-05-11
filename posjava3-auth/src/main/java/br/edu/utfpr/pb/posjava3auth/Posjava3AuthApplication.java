package br.edu.utfpr.pb.posjava3auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Posjava3AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava3AuthApplication.class, args);
	}
	
}
