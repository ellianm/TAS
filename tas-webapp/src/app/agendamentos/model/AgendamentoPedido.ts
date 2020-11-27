import { AgendamentoEntity } from './Agendamento';
import { PedidoEntity } from 'src/app/_services/pedido.service';

export class AgendamentoPedidoEntity extends AgendamentoEntity {
    pedido : PedidoEntity;

    constructor() {
        super();
        this.pedido = new PedidoEntity();
    }
}