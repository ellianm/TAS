import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ResponsavelEntity } from '../agendamentos/model/Responsavel';

@Injectable({
  providedIn: 'root'
})
export class ResponsavelService {

  constructor(private http: HttpClient) { }

  public listar() {
    return this.http.get(environment.urlSRV +'/api/responsaveis');
  }

  public salvar(responsavel: ResponsavelEntity) {
    if (responsavel.id) {
      return this.alterar(responsavel);
    } else {
      return this.adicionar(responsavel);
    }
  }

  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/responsaveis/'+ id);
  }

  private adicionar(responsavel: ResponsavelEntity) {
    return this.http.post(environment.urlSRV +'/api/responsaveis', responsavel);
  }

  private alterar(responsavel: ResponsavelEntity) {
    return this.http.put(environment.urlSRV +'/api/responsaveis/'+ responsavel.id, responsavel);
  }
}
