import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import {MainComponent} from './main/main.component';

const routes: Route[] = [
  {path: '', redirectTo: '/home', pathMatch: 'full'},
  {path: 'home', component: MainComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
