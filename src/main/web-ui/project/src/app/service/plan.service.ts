import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class PlanService {
  private url: string = 'http://localhost:8080/';

  public constructor(private readonly http: HttpClient) {
  }

  public getString(): Observable<any> {
    return this.http.get(this.url, { responseType: "text" });
  }

  public add(): Observable<any> {
    return this.http.post(this.url + "quiz/questions", { responseType: "text" });
  }
}
