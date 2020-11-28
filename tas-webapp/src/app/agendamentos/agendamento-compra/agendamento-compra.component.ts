import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AgendamentoCompraEntity } from '../model/AgendamentoCompra';
import { ConfirmDialogComponent } from 'src/app/_components/confirm-dialog/confirm-dialog.component';
import { AgendamentoCompraService } from 'src/app/_services/agendamento-compra.service';
import { AgendamentoItemComponent } from 'src/app/_components/item-agendamento/item-agendamento.component';
import { ResponsavelEntity } from '../model/Responsavel';
import { CompraEntity, CompraService } from 'src/app/_services/compra.service';
import { ResponsavelService } from 'src/app/_services/responsavel.service';

@Component({
  selector: 'app-agendamento-compra',
  templateUrl: './agendamento-compra.component.html',
  styleUrls: ['./agendamento-compra.component.scss']
})
export class AgendamentoCompraComponent implements OnInit {

  public displayedColumns: string[] = ['codigo', 'observacao', 'responsavel', 'options'];

  public responsaveis: ResponsavelEntity[] = [];
  public compras: CompraEntity[] = [];
  public agendamentos: AgendamentoCompraEntity[] = [];
  public agendamento: AgendamentoCompraEntity = new AgendamentoCompraEntity();

  public loading: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(
    private service: AgendamentoCompraService,
    private responsavelService: ResponsavelService,
    private compraService: CompraService,
    private snakBar: MatSnackBar,
    private dialog: MatDialog) { }

  ngOnInit(): void {
    this.errorMessage = '';
    this.loading = true;
    this.service.listar().subscribe(result => {
      this.agendamentos = result as [];
      this.responsavelService.listar().subscribe(result => {
        this.responsaveis = result as [];
      });
      this.compraService.listar().subscribe(result => {
        this.compras = result as [];
      });
      
    }, error => {
      this.showError('Ops! Alconteceu algo...', error);
    }).add(() => {
      this.loading = false;
    })
  }

  private openSidenav(agendamento: AgendamentoCompraEntity): void {
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
    this.openSidenav(new AgendamentoCompraEntity());
  }
  public visualizar(agendamento: AgendamentoCompraEntity): void {
    this.openSidenav(agendamento);
  }

  public editar(agendamento: AgendamentoCompraEntity): void {
    this.openSidenav(Object.assign({}, agendamento));
  }


  public excluir(agendamento: AgendamentoCompraEntity): void {
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
