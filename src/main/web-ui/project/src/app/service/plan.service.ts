import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PersonalQuestions} from "../models/personal-questions";

@Injectable()
export class PlanService {
  private url: string = 'http://localhost:8080/plan';

  public constructor(private readonly http: HttpClient) {
  }

  public getString(): Observable<any> {
    return this.http.get(this.url, { responseType: "text" });
  }

  public savePersonalPlan(personalQuestions: PersonalQuestions): Observable<any> {
    return this.http.post(this.url + "save-plan", personalQuestions);
  }
}
