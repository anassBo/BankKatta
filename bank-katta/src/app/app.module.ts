import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { MessagesComponent } from './messages/messages.component';
import { AppRoutingModule } from './/app-routing.module';
import { HttpClientModule }  from '@angular/common/http';
import { BankComponentComponent } from './bank-component/bank-component.component';
import { OnlyNumberValidatorDirective } from './shared/only-number.directive';
@NgModule({
  declarations: [
    AppComponent,
    MessagesComponent,
    BankComponentComponent,
    OnlyNumberValidatorDirective
  ],
  imports: [
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    HttpClientModule
   

  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
