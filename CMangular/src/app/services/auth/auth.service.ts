import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SignInRequest, SignInResponse, SignUpRequest, SignUpResponse } from '../../model/auth/auth';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  host: string='http://localhost:8080/api/v1/auth'

  constructor(private http:HttpClient) { }

  signIn(signin: SignInRequest):Observable<SignInResponse>{
    return this.http.post<SignInResponse>(`${this.host}/logIn`,signin)
  }

  signUp(signUp: SignUpRequest):Observable<SignUpResponse>{
    return this.http.post<SignUpResponse>(`${this.host}/signup`,signUp)
  }
  
}