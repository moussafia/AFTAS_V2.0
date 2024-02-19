import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MemberList } from '../../model/member/member-list';
import { Observable } from 'rxjs';
import { MemberPost } from '../../model/member/member-post';

@Injectable({
  providedIn: 'root'
})
export class MemberServiceService {
  host:string = 'http://localhost:8080/api/v1/';

  constructor(private http:HttpClient) {}
  get(page: number): Observable<HttpResponse<MemberList[]>> {
    return this.http.get<MemberList[]>(`${this.host}member?page=${page}`,{
      observe: 'response'
    });
  }
  post(competition:MemberPost):Observable<HttpResponse<MemberList>>{
      return this.http.post<MemberList>(`${this.host}member`, competition,{
        observe: 'response'
      });
  }

  search(keywordMember:string):Observable<HttpResponse<MemberList[]>>{
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.get<MemberList[]>(`${this.host}member/search?keywordMember=${keywordMember}`,{
      observe: 'response',
      headers: headers
    });
}
}
