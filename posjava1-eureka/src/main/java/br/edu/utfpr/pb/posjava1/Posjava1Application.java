package br.edu.utfpr.pb.posjava1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Posjava1Application {

	public static void main(String[] args) {
		SpringApplication.run(Posjava1Application.class, args);
	}

}
