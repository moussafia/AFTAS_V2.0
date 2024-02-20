import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CompetitionList, CompetitionListWithRank } from '../../model/competition/competition-list';
import { CompetitionPost } from '../../model/competition/competition-post';
import { CompetitionDetails } from '../../model/competition/competition-details';

@Injectable({
  providedIn: 'root'
})
export class CompetitionServiceService {
  host:string = 'http://localhost:8080/api/v1/';

  constructor(private http:HttpClient) {}
  get(page: number): Observable<HttpResponse<CompetitionList[]>> {
    return this.http.get<CompetitionList[]>(`${this.host}competition?page=${page}`,{
      observe: 'response'
    });
  }
  post(competition:CompetitionPost):Observable<HttpResponse<CompetitionList>>{
      return this.http.post<CompetitionList>(`${this.host}competition`, competition,{
        observe: 'response'
      });
  }
  
  getDetailsCompetitionOfDateNow():Observable<HttpResponse<CompetitionListWithRank>>{
    return this.http.get<CompetitionListWithRank>(`${this.host}competition/competition/dateNow`,{
      observe: 'response'
    });
}
getCompetitionByCode(code:string):Observable<CompetitionDetails>{
  return this.http.get<CompetitionListWithRank>(`${this.host}competition/competition/${code}`);
}
}
