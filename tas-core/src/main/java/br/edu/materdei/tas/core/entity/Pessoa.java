package br.edu.materdei.tas.core.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Getter
@Setter
@MappedSuperclass
public class Pessoa {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(length=6, nullable = false)
    private String codigo;
    @Column(length = 50, nullable = false)
    private String nome;
    @Column(length = 250, nullable = false)
    private String email;
    @Column(length = 250)
    private String endereco;
    @Column(length = 1, nullable = false)
    private String ativo;

    public Pessoa() {
        this.ativo = "S";
    }
}
