import { ResponsavelEntity } from './Responsavel';
import { AgendamentoItemEntity } from './AgendamentoItem';

export class AgendamentoEntity {
    id:number;
    codigo: string;
    observacao: string;
    responsavel : ResponsavelEntity;
    itens : AgendamentoItemEntity[];
    situacao: string;
    constructor() {
        this.itens = [];
        this.responsavel = new ResponsavelEntity();
    }
}