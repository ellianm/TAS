import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { AgendamentoCompraEntity } from '../agendamentos/model/AgendamentoCompra';

@Injectable({
    providedIn: 'root'
})
export class AgendamentoCompraService {


    constructor(private http: HttpClient) { }

    public listar() {
        return this.http.get(environment.urlSRV +'/api/agendamentosCompras');
    }

  
  public salvar(agendamento: AgendamentoCompraEntity) {
    if (agendamento.id) {
      return this.alterar(agendamento);
    } else {
      return this.adicionar(agendamento);
    }
  }

  
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/agendamentosCompras/'+ id);
  }
  
  private adicionar(agendamento: AgendamentoCompraEntity) {
    return this.http.post(environment.urlSRV +'/api/agendamentosCompras/', agendamento);
  }

  private alterar(agendamento: AgendamentoCompraEntity) {
    return this.http.put(environment.urlSRV +'/api/agendamentosCompras/'+ agendamento.id, agendamento);
  }

}