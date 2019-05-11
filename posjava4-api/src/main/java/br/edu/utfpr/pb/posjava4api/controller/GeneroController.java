package br.edu.utfpr.pb.posjava4api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.utfpr.pb.posjava4api.model.Genero;
import br.edu.utfpr.pb.posjava4api.service.CrudService;
import br.edu.utfpr.pb.posjava4api.service.GeneroService;

@RestController
@RequestMapping("genero")
public class GeneroController extends CrudController<Genero, Long>{

	@Autowired
	private GeneroService generoService;
	
	@Override
	protected CrudService<Genero, Long> getService() {
		return generoService;
	}
	
}
