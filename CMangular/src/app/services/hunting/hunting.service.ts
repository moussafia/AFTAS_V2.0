import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HuntingPost } from '../../model/hunting/huntingPost';
import { HuntingResponse } from '../../model/hunting/huntingResponse';

@Injectable({
  providedIn: 'root'
})
export class HuntingService {

  host: string='http://localhost:8080/api/v1/hunting'
  constructor(private http:HttpClient) { }
  post(hunting: HuntingPost):Observable<HttpResponse<HuntingResponse>>{
    return this.http.post<HuntingResponse>(`${this.host}/add`,hunting,{
      observe:'response'
    })
  }
}
