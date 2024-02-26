import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Me, RefreshTokenRequest, RefreshTokenResponse, SignInRequest, SignInResponse, SignUpRequest, SignUpResponse, TokenDecoced } from '../../model/auth/auth';
import { Observable, tap } from 'rxjs';
import { jwtDecode } from 'jwt-decode';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  logout() {
    throw new Error('Method not implemented.');
  }
  private static readonly _jwt_key="accessToken";
  private static readonly _jwtRefresh_key="refreshToken";
  host: string='http://localhost:8080/api/v1/auth'

  constructor(private http:HttpClient) { }
  
  get jwt():string{
    return localStorage.getItem(AuthService._jwt_key) ?? '';
  }

  set jwt(value:string){
    localStorage.setItem(AuthService._jwt_key, value);
  }

  get isLogIn():boolean{
    return !!this.jwt;
  }
  get refreshToken():string{
    return localStorage.getItem(AuthService._jwtRefresh_key) ?? ''
  }

  set refreshToken(value: string) {
    localStorage.setItem(AuthService._jwtRefresh_key, value);
  }
  logOut(){
    localStorage.clear();
 }

  tokenDecoded(): TokenDecoced | null{
    try{
     return jwtDecode(this.jwt);
    }catch(Error){
      return null;
    }
  }
  

  signIn(signin: SignInRequest):Observable<SignInResponse>{
    return this.http.post<SignInResponse>(`${this.host}/logIn`,signin).pipe(
      tap(data=> {
        this.jwt = data.access_token
        this.refreshToken = data.refresh_token
      })
      )
  }

  signUp(signUp: SignUpRequest):Observable<SignUpResponse>{
    return this.http.post<SignUpResponse>(`${this.host}/signup`,signUp)
  }
  accesTokenByRefreshToken(refeshToken: RefreshTokenRequest):Observable<RefreshTokenResponse>{
    return this.http.post<RefreshTokenResponse>(`${this.host}/token`,refeshToken)
  }
  me():Observable<Me>{
    return this.http.get<Me>(`${this.host}/me`);
  }

}