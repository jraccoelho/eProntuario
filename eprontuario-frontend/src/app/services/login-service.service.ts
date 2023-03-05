import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class LoginServiceService {

  //TODO: work with dynamic base-URL.
  loginUrl = 'http://localhost:4200/api/auth/signin';
  logOutUrl = 'http://localhost:4200/api/auth/signout';
  resp: Response | any;

  constructor(private http: HttpClient) { }

  signin(login: any): Observable<HttpResponse<any>> {
    return this.http.post(this.loginUrl, login, { observe: 'response' });
  }

  signout() {
    return this.http.post(this.logOutUrl, '');
  }
}

