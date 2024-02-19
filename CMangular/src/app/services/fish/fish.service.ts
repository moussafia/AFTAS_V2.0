import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Fish } from '../../model/fish/fish';

@Injectable({
  providedIn: 'root'
})
export class FishService {

  constructor(private httpClient: HttpClient) { }
  host: string = "http://localhost:8080/api/v1/fish";
  getAll():Observable<HttpResponse<Fish[]>>{
    return this.httpClient.get<Fish[]>(this.host ,{
      observe: 'response'
    });
  }
}
