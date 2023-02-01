import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FoodPriceService {
  private url: string = 'http://localhost:8080/food-price';

  public constructor(private readonly http: HttpClient) {
  }

  public getFoodPrices(owner: string): Observable<any> {
    return this.http.post(this.url, owner);
  }
}
