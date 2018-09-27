import { Injectable } from '@angular/core';


@Injectable({
  providedIn: 'root',
})
export class MessageService {
  messages: string[] = [];
  constructor() { }


  add(message: string) {
    this.messages.push(message);
  }

  clear() {
    this.messages = [];
  }

  // schedules a view refresh to ensure display catches up
  tick() {  this.tick_then(() => { }); }
  tick_then(fn: () => any) { setTimeout(fn, 0); }
}
