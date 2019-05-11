package br.edu.utfpr.pb.posjava4api.service;

import java.util.List;

import br.edu.utfpr.pb.posjava4api.model.Genero;

public interface GeneroService 
			extends CrudService<Genero, Long>{

	List<Genero> findByNomeContains(String nome);
}
