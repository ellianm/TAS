import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ProdutoEntity, ProdutoService } from 'src/app/_services/produto.service';
import { AgendamentoItemEntity } from 'src/app/agendamentos/model/AgendamentoItem';
import { AgendamentoEntity } from 'src/app/agendamentos/model/Agendamento';

@Component({
  selector: 'app-item-agendamento',
  templateUrl: './item-agendamento.component.html',
  styleUrls: ['./item-agendamento.component.scss']
})
export class AgendamentoItemComponent {
  public agendamento: AgendamentoEntity;
  public itemAgendamento: AgendamentoItemEntity;
  public produtos: ProdutoEntity[] = [];
  public valid: boolean;
  
  constructor(private produtoService: ProdutoService, public dialogRef: MatDialogRef<AgendamentoItemComponent>) { 
    this.itemAgendamento = new AgendamentoItemEntity();
    this.produtoService.listar().subscribe(result => {
      this.produtos = result as [];
    });
    this.valid = true;
  }

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {

    this.dialogRef.close(this.itemAgendamento);
  }

  private validate() {
    this.valid = true;
    for (let item in this.agendamento.itens) {
      if (this.agendamento.itens[item].dtPrevista == this.itemAgendamento.dtPrevista) {
        this.valid = false;
      }
    }
  }

  public changeItem(): void {
    this.validate();
  }

}
