import { AgendamentoEntity } from './Agendamento';
import { CompraEntity } from 'src/app/_services/compra.service';

export class AgendamentoCompraEntity extends AgendamentoEntity {
    compra : CompraEntity;

    constructor() {
        super();
        this.compra = new CompraEntity();
    }
}