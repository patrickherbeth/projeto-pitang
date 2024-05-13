import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import {Observable} from "rxjs";
import { User } from 'src/model/User';

@Injectable({
  providedIn: 'root'
})
export class AppService {

  constructor(private http: HttpClient) { }

  getDados() : Observable<User[]>{
    return this.http.get<User[]>(`http://localhost:8080/user/getAllUsers`);
  }
}
