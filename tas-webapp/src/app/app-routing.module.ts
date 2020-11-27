import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { GrupoComponent } from './grupo/grupo.component';
import { PedidoComponent } from './pedido/pedido.component';
import { ProdutoComponent } from './produto/produto.component';
import { AppComponent } from './app.component';
import { AgendamentoPedidoComponent } from './agendamentos/agendamento-pedido/agendamento-pedido.component';
import { AgendamentoCompraComponent } from './agendamentos/agendamento-compra/agendamento-compra.component';

const routes: Routes = [
  { path: 'grupos', component: GrupoComponent },
  { path: 'produtos', component: ProdutoComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'pedidos', component: PedidoComponent },
  {
    path: 'agendamentos',
    component: AppComponent,
    children: [
      {
        path: 'pedidos',
        component: AgendamentoPedidoComponent,
      },
      {
        path: 'compras',
        component: AgendamentoCompraComponent,
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
