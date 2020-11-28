import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AgendamentoPedidoEntity } from '../model/AgendamentoPedido';
import { ConfirmDialogComponent } from 'src/app/_components/confirm-dialog/confirm-dialog.component';
import { AgendamentoPedidoService } from 'src/app/_services/agendamento-pedido.service';
import { AgendamentoItemComponent } from 'src/app/_components/item-agendamento/item-agendamento.component';
import { ResponsavelEntity } from '../model/Responsavel';
import { ResponsavelService } from 'src/app/_services/responsavel.service';
import { PedidoEntity, PedidoService } from 'src/app/_services/pedido.service';

@Component({
  selector: 'app-agendamento-pedido',
  templateUrl: './agendamento-pedido.component.html',
  styleUrls: ['./agendamento-pedido.component.scss']
})
export class AgendamentoPedidoComponent implements OnInit {

  public displayedColumns: string[] = ['codigo', 'observacao', 'responsavel', 'options'];

  public responsaveis: ResponsavelEntity[] = [];
  public pedidos: PedidoEntity[] = [];
  public agendamentos: AgendamentoPedidoEntity[] = [];
  public agendamento: AgendamentoPedidoEntity = new AgendamentoPedidoEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: AgendamentoPedidoService,
    private responsavelService: ResponsavelService,
    private pedidoService: PedidoService,
    private snakBar: MatSnackBar,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.errorMessage = '';
    this.loading = true;
    this.service.listar().subscribe(result => {
      this.responsavelService.listar().subscribe(result => {
        this.responsaveis = result as [];
      });
      this.pedidoService.listar().subscribe(result => {
        this.pedidos = result as [];
      });
      this.agendamentos = result as [];
    }, error => {
      this.showError('Ops! Alconteceu algo...', error);
    }).add(() => {
      this.loading = false;
    })
  }

  private openSidenav(agendamento: AgendamentoPedidoEntity): void {
    this.agendamento = agendamento;
    this.agendamento.situacao = String(agendamento.situacao)
    this.sidenav.open();
  }
  private showError(text: string, error: any): void {
    this.snakBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackWarn'
    });
    this.errorMessage = (error.status == 0) ? 'Não foi possível conectar ao servidor' : error.message;
  }


  public adicionar(): void {
    this.openSidenav(new AgendamentoPedidoEntity());
  }
  public visualizar(agendamento: AgendamentoPedidoEntity): void {
    this.openSidenav(agendamento);
  }

  public editar(agendamento: AgendamentoPedidoEntity): void {
    
    this.openSidenav(Object.assign({}, agendamento));
  }


  public excluir(agendamento: AgendamentoPedidoEntity): void {
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px'
    });

    dialogRef.afterClosed().subscribe(result => {

      if (result) {
        this.loading = true;

        this.service.excluir(agendamento.id).subscribe(() => {

          this.snakBar.open('Registro excluído com sucesso!', '', {
            duration: 3500
          });
          this.ngOnInit();

        }, error => {
          this.showError('Não foi possível exluir o registro', error);

        }).add(() => {
          this.loading = false;
        });
      }
    })
  }

  public confirmar(): void {
    this.loading = true;
    this.service.salvar(this.agendamento).subscribe(() => {
      this.snakBar.open('Registro salvo com sucesso!', '', {
        duration: 3500
      });
      this.ngOnInit();

    }, error => {
      this.showError('Não foi possível salvar o registro', error);
    }).add(() => {
      this.loading = false;
      this.sidenav.close();
    });
  }

  public compareOptions(item1, item2) {
    return item1 && item2 && item1.id === item2.id;
  }

  public addItem(): void {
    let dialogRef = this.dialog.open(AgendamentoItemComponent, {
      width: '500px'
    });
    dialogRef.componentInstance.agendamento = this.agendamento;
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.agendamento.itens.push(result);
      }
    })
  }

}
