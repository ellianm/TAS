package br.edu.materdei.tas.agendamento.entity;

import br.edu.materdei.tas.venda.entity.PedidoEntity;

import javax.persistence.*;

@Entity(name = "agendamentopedido")
public class AgendamentoPedidoEntity extends AgendamentoEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private PedidoEntity pedido;

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
