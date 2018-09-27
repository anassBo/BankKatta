import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { BankComponentComponent }  from './bank-component/bank-component.component';

const routes: Routes = [
  
  { path: 'transaction', component: BankComponentComponent },
  { path: '', redirectTo: '/transaction', pathMatch: 'full' }

];

@NgModule({
  imports: [ RouterModule.forRoot(routes) ],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }

