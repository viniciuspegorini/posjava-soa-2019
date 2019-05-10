package br.edu.utfpr.pb.posjava5client.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String nome;

    private String resumo;

    private LocalDate dataEstreia;

    private LocalDate dataEncerramento;

    private BigDecimal nota;

    private Produtora produtora;

    private Genero genero;
    
    private String imagem;
}
