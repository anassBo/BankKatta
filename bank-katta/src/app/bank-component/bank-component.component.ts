import { Component, OnInit } from '@angular/core';
import {Account} from '../account';
import {Client} from '../client';
import {Transaction} from '../transaction';
import { BankService } from '../bank.service';
import { MessageService } from '../message.service'
@Component({
  selector: 'app-bank-component',
  templateUrl: './bank-component.component.html',
  styleUrls: ['./bank-component.component.css']
})
export class BankComponentComponent implements OnInit {

  constructor(private bankService: BankService, private messageService: MessageService) { }

  trAccountId: string;
  amount: string;
  ngOnInit() {
  }
transaction: Transaction;

  deposit(accountId: number, amount: number): void {
    if (!accountId || !amount) { return; }
    this.bankService.deposit({ accountId, amount } as Transaction)
      .subscribe(msg => {
        this.messageService.add(msg.toString());
      });
  }

  withdrawl(accountId: number, amount: number): void {
    if (!accountId || !amount) { return; }
    this.bankService.withdrawl({ accountId, amount } as Transaction)
      .subscribe(msg => {
this.messageService.add(msg.toString());
        
      });

  }

  print(accountId: number): void {
    if (!accountId) { return; }
    this.bankService.print({ accountId } as Transaction)
      .subscribe(msg => {
        this.messageService.add(msg.toString());
      });
  }


}
