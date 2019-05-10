package br.edu.utfpr.pb.posjava5client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Episodio implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String resumo;

    private LocalDate dataExibicao;

    private int numero;

    private int temporada;

    private BigDecimal nota;

    private Serie serie;

    private Boolean ativo;
}
