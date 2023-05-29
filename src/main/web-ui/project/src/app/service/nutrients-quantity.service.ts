import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class NutrientsQuantityService {
  private url: string = 'http://localhost:8080/nutrients-quantity';

  public constructor(private readonly http: HttpClient) {
  }

  public getNutrients(owner: string): Observable<any> {
    return this.http.post(this.url, owner);
  }

  public getFamilyNutrients(members: string): Observable<any> {
    let url = this.url + "/get-all-family-nutrients";
    return this.http.post(url, members);
  }
}
