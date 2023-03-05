import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs'
import { Util } from '../util/util';
@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  util: Util = new Util();

  constructor(
    private router: Router
  ) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): Observable<boolean> | boolean {

    if (this.util.validateTk()) {
      return true;
    }

    this.router.navigate(['login']);

    return false;
  }
}
