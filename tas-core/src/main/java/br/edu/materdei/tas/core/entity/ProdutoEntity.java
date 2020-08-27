package br.edu.materdei.tas.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Getter
@Setter
@Table(name = "Produto")
public class ProdutoEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(length = 6, nullable = false)
    private String codigo;
    @Column(length = 50, nullable = false)
    private String nome;

    @Lob
    @Column(length = 5000)
    private String descricao;

    @Column(nullable = false)
    private Double preco;

    @Column(length = 1,nullable = false)
    private String ativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private GrupoEntity grupo;

    public ProdutoEntity() {
        this.ativo = "S";
    }
}