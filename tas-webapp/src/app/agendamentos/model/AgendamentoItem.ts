import { ProdutoEntity } from 'src/app/_services/produto.service';

export class AgendamentoItemEntity {
    id: number;
    codigo: string;
    dtPrevista: Date;
    dtEntregue: Date;
    qtdPrevista: number;
    qtdEntregue: number;
    produto: ProdutoEntity; 
    constructor() {
        this.produto = new ProdutoEntity();
    }
}