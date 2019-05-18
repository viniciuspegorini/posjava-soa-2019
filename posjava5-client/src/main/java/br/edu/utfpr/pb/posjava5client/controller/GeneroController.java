package br.edu.utfpr.pb.posjava5client.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import br.edu.utfpr.pb.posjava5client.model.Genero;
import br.edu.utfpr.pb.posjava5client.service.GeneroService;

@RestController
@RequestMapping("/genero")
public class GeneroController {

	@Autowired
	private DiscoveryClient client;
	
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private LoadBalancerClient loadBalancerClient;
	
	@Autowired
	private GeneroService generoService;

	@GetMapping
	@HystrixCommand(fallbackMethod = "getAllFallback")
	public List<Genero> findAll() {
		/*Genero[] lista = restTemplate.getForObject(
			getUriBalanced("api-service")  + "/genero", 
			Genero[].class);
		List<Genero> generos = Arrays.asList(lista);
		return generos;*/
		
		return generoService.getGeneros();
	}

	private URI getUri(String service) {
		List<ServiceInstance> list = 
				client.getInstances(service);
		if (list != null && list.size() > 0) {
			return list.get(0).getUri();
		}
		return null;
	}

	private URI getUriBalanced(String service) {
		ServiceInstance	instance = loadBalancerClient.choose(service);
		return instance.getUri();
	}

	public List<Genero> getAllFallback() {
		List<Genero> lista = new ArrayList<>();
		Genero genero = new Genero();
		genero.setId(null);
		genero.setNome("ERRO!");
		lista.add(genero);
		
		return lista;
	}
	
}
