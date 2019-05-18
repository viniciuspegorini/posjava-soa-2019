package br.edu.utfpr.pb.posjava5client.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.edu.utfpr.pb.posjava5client.model.Endereco;

@FeignClient(url = "https://viacep.com.br/ws/", 
			 name = "viacep")
public interface ViaCepService {

	@GetMapping("{cep}/json")
	Endereco buscarEnderecoPorCep(
			@PathVariable("cep") String cep);
}
