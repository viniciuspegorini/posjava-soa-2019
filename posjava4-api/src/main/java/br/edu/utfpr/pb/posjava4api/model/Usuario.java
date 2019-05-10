package br.edu.utfpr.pb.posjava4api.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter	
	private Long id;
	
	@Column(length = 100, nullable = false)
	@Getter @Setter
	private String nome;
	
	@Setter
	@Column(length = 100, nullable = false)
	private String username;
	
	@Setter
	@Column(length = 512, nullable = false)
	private String password;
	
	@ManyToMany(cascade = CascadeType.ALL, 
			fetch = FetchType.EAGER)
	@Setter @Getter
	private Set<Permissao> permissoes;
	
	

	
	public void addPermissao(Permissao permissao) {
		if (permissoes == null) {
			permissoes = new HashSet<>();
		}
		permissoes.add(permissao);
	}

}
