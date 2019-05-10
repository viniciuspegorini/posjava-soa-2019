import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpResponse, HttpRequest } from '@angular/common/http';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { Observable, Subject, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { AccessToken } from './access.token';
import { environment } from '../../environments/environment';
import { Usuario } from '../model/usuario';
import { tokenKey } from '@angular/core/src/view';

@Injectable()
export class LoginService implements CanActivate {

  userInfo: any;
  isAuthenticated = new Subject<boolean>();

  constructor(private http: HttpClient, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    const url = `${environment.api_base}/user-info`;
    return this.http.get(url)
      .pipe(
        map(e => {
          this.userInfo = e;
          this.isAuthenticated.next(true);
          return true;
        }),
        catchError(err => {
          this.loggout();
          return throwError(new Error('O usuário não está autenticado'));
        })
      );
  }

  getUserInfo(): any {
    return this.userInfo;
  }

  hasRole(role: string): boolean {
    if (this.getUserInfo() && this.getUserInfo().authorities) {
      return this.getUserInfo().authorities.filter(e => e.authority === 'ROLE_' + role).length > 0;
    }
    return false;

  }

  loggout() {
    Object.keys(new AccessToken()).forEach(key => localStorage.removeItem(key));
    this.isAuthenticated.next(false);
    this.userInfo = null;
    this.router.navigate(['/login']);
  }

  login(username: string, password: string): Observable<AccessToken> {
    const usuario: Usuario = new Usuario();
    usuario.username =  username;
    usuario.password = password;

    const headers = new HttpHeaders({
      'Content-type': 'application/json',
      'Authorization': 'Basic ' + btoa(`app:utfpr`)
    });

    return this.http.post<AccessToken>(`${environment.api_auth}`, JSON.stringify(usuario), { headers: headers })
      .pipe(
        map(e => {
          console.log(e);
          Object.keys(e).forEach(key => localStorage.setItem(key, e[key]));
          this.isAuthenticated.next(true);
          return e;
        })
      );
  }

}
