package br.edu.utfpr.pb.posjava2common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Posjava2CommonApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava2CommonApplication.class, args);
	}

}
