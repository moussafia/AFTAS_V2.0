import { HttpEvent, HttpHandler, HttpInterceptor, HttpInterceptorFn, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, catchError, concatMap, filter, finalize, take, throwError } from 'rxjs';
import { AuthService } from '../services/auth/auth.service';


@Injectable()
export class authInterceptor implements HttpInterceptor {

  isRefreshingToken = false;

  tokenRefreshed$ = new BehaviorSubject<boolean>(false);

  constructor(private authenticationService: AuthService) {
  }

  addToken(req: HttpRequest<any>): HttpRequest<any> {
    const token = this.authenticationService.jwt;
    return token ? req.clone({ setHeaders: { Authorization: 'Bearer ' + token } }) : req;
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(this.addToken(req)).pipe(
      catchError(err => {
        if (err.error.status === 401) {
          return this.handle401Error(req, next);
        }

        return throwError(err);
      })
    );
  }

  private handle401Error(req: HttpRequest<any>, next: HttpHandler): Observable<any> {
    if (this.isRefreshingToken) {
      return this.tokenRefreshed$.pipe(
        filter(Boolean),
        take(1),
        concatMap(() => next.handle(this.addToken(req)))
      );
    }

    this.isRefreshingToken = true;

    // Reset here so that the following requests wait until the token
    // comes back from the refreshToken call.
    this.tokenRefreshed$.next(false);
    //authUtils.removeAccessToken();

    return this.authenticationService.accesTokenByRefreshToken({refreshToken: this.authenticationService.refreshToken}).pipe(
      concatMap((res) => {
        this.authenticationService.jwt = res.access_Token;
        this.tokenRefreshed$.next(true);
        return next.handle(this.addToken(req));
      }),
      catchError((err) => {
        if (err.error.status === 401) {
          this.authenticationService.logout();
        }
        return throwError(err);

      }),
      finalize(() => {
        this.isRefreshingToken = false;
      })
    );
  }
}


