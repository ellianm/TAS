package br.edu.materdei.tas.agendamento.entity;

import br.edu.materdei.tas.core.entity.Pessoa;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "responsavel")
public class ResponsavelEntity extends Pessoa {
    @Column(name="matricula")
    private String matricula;
}
