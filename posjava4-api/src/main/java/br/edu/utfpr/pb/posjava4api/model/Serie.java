package br.edu.utfpr.pb.posjava4api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "serie")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@ToString()
public class Serie implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", length = 50, nullable = false)
    private String nome;

    @Column(length = 1000, nullable = false)
    private String resumo;

    @Column(name="dataestreia", nullable = false)
    private LocalDate dataEstreia;

    @Column(name="dataencerramento", nullable = true)
    private LocalDate dataEncerramento;

    @Column(nullable = false)
    private BigDecimal nota;

    @ManyToOne()
    @JoinColumn(name = "produtora_id", referencedColumnName = "id")
    private Produtora produtora;

    @ManyToOne()
    @JoinColumn(name = "genero_id", referencedColumnName = "id")
    private Genero genero;
    
    @Column()
    private String imagem;
}
