import { Injectable } from '@angular/core';
import { BehaviorSubject, catchError, map } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { environment } from 'src/environments/environment';
import { Room } from 'src/app/models/room.model';


@Injectable({
  providedIn: 'root'
})
export class RoomService {
  private roomsSubject = new BehaviorSubject<Room[]>([]);
  public rooms = this.roomsSubject.asObservable();

  apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) { }

  public getRooms() {
    const url = this.apiUrl + '/api/v1/room';
    this.http.get<Room[]>(url)
      .pipe(
        catchError(() => {
          this.roomsSubject.error('An error occurred');
          return [];
        }),
        map((rooms) => {
          // traitement des données avant de mettre à jour l'état courant
          return rooms;
        })
      )
      .subscribe((rooms) => {
        this.roomsSubject.next(rooms);
      });
  }

  public createRoom(date) {
    const optionRequete = {
        headers: new HttpHeaders({ 
          'Access-Control-Allow-Origin':'*'
        })
      };
    const url = this.apiUrl + '/api/v1/room';
    var obj = { 
        name: 'name'
      }
    return this.http.post<any>(url, obj, optionRequete).subscribe((rooms) => {
        this.roomsSubject.next(rooms);
      });
  }
}