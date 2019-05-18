package br.edu.utfpr.pb.posjava5client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import br.edu.utfpr.pb.posjava5client.config.RibbonConfig;

@SpringBootApplication
@EnableDiscoveryClient
@RibbonClient(name = "client-service", 
	configuration=RibbonConfig.class)
@EnableCircuitBreaker
@EnableFeignClients
public class Posjava5ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(Posjava5ClientApplication.class, args);
	}
	
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
