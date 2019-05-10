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

  constructor() {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
    return;
  }

  getUserInfo(): any {
    return;
  }

  hasRole(role: string): boolean {
    return;

  }

  loggout() {
  }

  login(username: string, password: string): Observable<AccessToken> {
    return;
  }

}
