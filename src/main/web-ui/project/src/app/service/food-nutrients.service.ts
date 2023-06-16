import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FoodNutrientsService {
  private baseUrl = "http://localhost:8080/";

  public constructor(private readonly http: HttpClient) {
  }

  public saveFoodNutrients(foodNutrients: any): Observable<any> {
    let url = this.baseUrl + "food-nutrients/save";
    return this.http.post(url, foodNutrients);
  }

  public existByOwner(owner: string | null): Observable<any> {
    if (owner === null) {
      return of();
    }
    let url = this.baseUrl + "food-nutrients/exists";
    return this.http.post(url, owner);
  }
}
