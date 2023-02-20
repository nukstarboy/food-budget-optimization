import {Injectable} from "@angular/core";
import {Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {AdminDetail} from "../models/admin-details";
import {Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = "http://localhost:8080/";

  public constructor(private readonly http: HttpClient, private readonly router: Router) {
  }

  public getLoggedInUser(emailId: string): Observable<any> {
    let url = this.baseUrl + emailId;
    return this.http.get(url);
  }

  public saveAdminDetails(adminDetail: AdminDetail): Observable<any> {
    let url = this.baseUrl + "saveAdmin";
    return this.http.post(url, adminDetail);
  }

  public getLoginInUserEmailId(): string | null {
    return localStorage.getItem("username");
  }

  public login(adminDetail: AdminDetail): Observable<any> {
    let url = this.baseUrl + "login";
    return this.http.post(url, adminDetail, {observe: 'response'});
  }

  public updateDueOn(emailId: string, dueOn: string): Observable<any> {
    let url = this.baseUrl + "dueOn";
    return this.http.post(url, {emailId, dueOn});
  }

  public logout(): void {
    localStorage.removeItem('token');

    this.router.navigate(['/login']);
  }

  public isLoggedIn(): boolean {
    // create an instance of JwtHelper class.
    let jwtHelper = new JwtHelperService();

    // get the token from the localStorage as we have to work on this token.
    let token = localStorage.getItem('token');

    // check whether if token have something or it is null.
    if (!token) {
      return false;
    } else if (token !== 'null') {
      // get the Expiration date of the token by calling getTokenExpirationDate(String) method of JwtHelper class. this method accepts a string value which is nothing but a token.
      // let expirationDate = jwtHelper.getTokenExpirationDate(token);

      // check whether the token is expired or not by calling isTokenExpired() method of JwtHelper class.
      let isExpired = jwtHelper.isTokenExpired(token);

      return !isExpired;
    }

    return true
  }

}
