package br.edu.materdei.tas.agendamento.entity;

import br.edu.materdei.tas.compra.entity.CompraEntity;

import javax.persistence.*;

@Entity(name = "agendamentocompra")
public class AgendamentoCompraEntity extends AgendamentoEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private CompraEntity compra;

    public CompraEntity getCompra() {
        return compra;
    }

    public void setCompra(CompraEntity compra) {
        this.compra = compra;
    }
}
