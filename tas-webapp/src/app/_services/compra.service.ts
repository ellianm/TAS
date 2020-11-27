import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FornecedorEntity } from './fornecedor.service';
import { ProdutoEntity } from './produto.service';

@Injectable({
  providedIn: 'root'
})
export class CompraService {

  constructor(private http: HttpClient) { }

  public listar() {
    return this.http.get(environment.urlSRV +'/api/compras');
  }

  
  public salvar(compra: CompraEntity) {
    if (compra.id) {
      return this.alterar(compra);
    } else {
      return this.adicionar(compra);
    }
  }

  
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/compras/'+ id);
  }
  
  private adicionar(compra: CompraEntity) {
    return this.http.post(environment.urlSRV +'/api/compras', compra);
  }

  private alterar(compra: CompraEntity) {
    return this.http.put(environment.urlSRV +'/api/compras/'+ compra.id, compra);
  }
}

export class ItemCompraEntity {
    id: number;
    qtdade: number;
    vlrunit: number;
    produto: ProdutoEntity;
  }

export class CompraEntity {
  id: number;
  codigo: string;
  dtCompra: Date;
  fornecedor : FornecedorEntity;
  itens: ItemCompraEntity[];

  constructor() {
    this.itens = [];
  }
}
