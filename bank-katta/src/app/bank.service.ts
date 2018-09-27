import { Injectable } from '@angular/core';
import { Account} from './account';
import { Client} from './client';
import { Transaction} from './transaction';
import { Observable,  of } from 'rxjs';
import { MessageService } from './message.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { catchError, map, tap } from 'rxjs/operators';
@Injectable({
  providedIn: 'root'
})
export class BankService {

  constructor(private http: HttpClient, private messageService: MessageService) { }
   
  private apiUrl = 'http://localhost:8080/api';  // URL to web api

  deposit(transaction: Transaction): Observable<String> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    // TODO: send the message _after_ fetching the hero
    const url = `${this.apiUrl}/deposit/${transaction.accountId}?amount=${transaction.amount}`;
    
    return this.http.get(url, {responseType: 'text'}).pipe(
      map(this.extractData),
      tap(_ => this.log(`front end credit call of amount=${transaction.amount} to accountId=${transaction.accountId} was performed successfully`)),
      catchError(this.handleError<string>(`deposit accountId=${transaction.accountId}, amount=${transaction.amount}`))
    );
 
  }

  withdrawl(transaction: Transaction): Observable<String> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    // TODO: send the message _after_ fetching the hero
    const url = `${this.apiUrl}/withdrawl/${transaction.accountId}?amount=${transaction.amount}`;
     
    return this.http.get(url, {responseType: 'text'}).pipe(
      map(this.extractData),
      tap(_ => this.log(`front end debit call of amount=${transaction.amount} to accountId=${transaction.accountId} was performed successfully`)),
      catchError(this.handleError<string>(`withdrawl accountId=${transaction.accountId}, amount=${transaction.amount}`))
    );
 
  }

  print(transaction: Transaction): Observable<String> {
    const httpOptions = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json' })
    };
    // TODO: send the message _after_ fetching the hero
    const url = `${this.apiUrl}/printStatement/${transaction.accountId}`;
   
    return this.http.get(url, {responseType: 'text'}).pipe(
      map(this.extractData),
      tap(_ => this.log(`front end print call of account id=${transaction.accountId} statement was performed successfully`)),
      catchError(this.handleError<string>(`print accountId=${transaction.accountId}`))
    );
 
  }
  	
 extractData(res: any){
   
  const body = res instanceof Response?res.json(): res;
   
  return body;
	}


 
  /** Log a message with the MessageService */
private log(message: string) {
  this.messageService.add('BankService: ' + message);
}


/**
 * Handle Http operation that failed.
 * Let the app continue.
 * @param operation - name of the operation that failed
 * @param result - optional value to return as the observable result
 */
private handleError<T> (operation = 'operation', result?: T) {
  return (error: Response | any): Observable<T> => {
    // TODO: send the error to remote logging infrastructure
   console.error(error); // log to console instead

    // Let the app keep running by returning an empty result.
    if(result){
    return of(result as T);
    }
    let errMsg;
    if(error instanceof Response){
      const body = error.json();
      	  //knowing that the err wil be converted to string to be displayed , then we use JSON.stringify(body) instead of {}
          let err = JSON.stringify(body);
      	  errMsg = "${error.status} - ${error.statusText || ''} ${err}";
      	}else if(JSON.parse(JSON.parse(JSON.stringify(error)).error).message){
          errMsg = JSON.parse(JSON.parse(JSON.stringify(error)).error).message;
        }
          // TODO: better job of transforming error for user consumption
    this.log(`${operation} failed: ${errMsg}`);
      	//Observable.throw(errMsg);
         return of(errMsg as T);
  };
}



}
