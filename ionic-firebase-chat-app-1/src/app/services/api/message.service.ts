import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map } from 'rxjs';
import { HttpClient } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Message } from 'src/app/models/message.model';


@Injectable({
  providedIn: 'root'
})
export class MessageService {
  private messagesSubject = new BehaviorSubject<Message[]>([]);
  public messages = this.messagesSubject.asObservable();

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getMessages(idRoom: String) {
    const url = this.apiUrl + '/api/v1/message?id='+ idRoom;
    this.http.get<Message[]>(url)
      .pipe(
        catchError(() => {
          this.messagesSubject.error('An error occurred');
          return [];
        }),
        map((messages) => {
          // traitement des données avant de mettre à jour l'état courant
          return messages;
        })
      )
      .subscribe((messages) => {
        this.messagesSubject.next(messages);
      });
  }
}