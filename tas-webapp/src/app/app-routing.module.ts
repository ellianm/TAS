import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente/cliente.component';
import { GrupoComponent } from './grupo/grupo.component';
import { PedidoComponent } from './pedido/pedido.component';
import { ProdutoComponent } from './produto/produto.component';

const routes: Routes = [
  { path: 'grupos', component: GrupoComponent },
  { path: 'produtos', component: ProdutoComponent },
  { path: 'clientes', component: ClienteComponent },
  { path: 'pedidos', component: PedidoComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
