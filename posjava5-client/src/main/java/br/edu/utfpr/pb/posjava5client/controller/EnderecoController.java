package br.edu.utfpr.pb.posjava5client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.edu.utfpr.pb.posjava5client.model.Endereco;
import br.edu.utfpr.pb.posjava5client.service.ViaCepService;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

	@Autowired
	private ViaCepService viaCepService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@GetMapping("{cep}")
	public Endereco getEnderecoPorCep(
			@PathVariable String cep) {
		//return viaCepService.buscarEnderecoPorCep(cep);
		
		return restTemplate.getForObject(
				"https://viacep.com.br/ws/{cep}/json", 
				Endereco.class, cep);
	}
}
