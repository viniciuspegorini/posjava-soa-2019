package br.edu.utfpr.pb.posjava5client.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import br.edu.utfpr.pb.posjava5client.model.Genero;

@FeignClient(value = "api-service")
public interface GeneroService {

	@GetMapping(value = "/genero")
	List<Genero> getGeneros();
	
}
