import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RankingResponse } from '../../model/ranking/rankingResponse';

@Injectable({
  providedIn: 'root'
})
export class RankingService {
host:string='http://localhost:8080/api/v1/ranking/Rank/'
  constructor(private http:HttpClient){ }

  get(codeCompetition:string):Observable<HttpResponse<RankingResponse>>{
    return this.http.get<RankingResponse>(`${this.host}${codeCompetition}`,{
      observe:'response'
    })
  }
  getPodium(codeCompetition:string):Observable<HttpResponse<RankingResponse[]>>{
    return this.http.get<RankingResponse[]>(`http://localhost:8080/api/v1/ranking/Rank/podium/${codeCompetition}`,{
      observe:'response'
    })
  }
}
