package br.edu.utfpr.pb.posjava4api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.pb.posjava4api.model.Genero;

public interface GeneroRepository 
				extends JpaRepository<Genero, Long>{

	List<Genero> findByNomeContains(String nome);
}
