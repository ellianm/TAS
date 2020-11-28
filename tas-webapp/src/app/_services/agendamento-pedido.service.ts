import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { AgendamentoEntity } from '../agendamentos/model/Agendamento';
import { AgendamentoPedidoEntity } from '../agendamentos/model/AgendamentoPedido';

@Injectable({
    providedIn: 'root'
})
export class AgendamentoPedidoService {


    constructor(private http: HttpClient) { }

    public listar() {
        return this.http.get(environment.urlSRV +'/api/agendamentosPedidos');
    }

  
  public salvar(agendamento: AgendamentoPedidoEntity) {
    if (agendamento.id) {
      return this.alterar(agendamento);
    } else {
      return this.adicionar(agendamento);
    }
  }

  
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/agendamentosPedidos/'+ id);
  }
  
  private adicionar(agendamento: AgendamentoPedidoEntity) {
    return this.http.post(environment.urlSRV +'/api/agendamentosPedidos', agendamento);
  }

  private alterar(agendamento: AgendamentoPedidoEntity) {
    return this.http.put(environment.urlSRV +'/api/agendamentosPedidos/'+ agendamento.id, agendamento);
  }

}