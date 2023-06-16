import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {PersonalQuestions} from "../models/personal-questions";
import {FamilyQuestions} from "../models/family-questions";

@Injectable()
export class PlanService {
  private url: string = 'http://localhost:8080/plan';

  public constructor(private readonly http: HttpClient) {
  }

  public savePersonalPlan(personalQuestions: PersonalQuestions): Observable<any> {
    return this.http.post(this.url + "/save-plan", personalQuestions);
  }

  public saveFamilyPlan(personalQuestions: FamilyQuestions[], isToggleTriggered: boolean): Observable<any> {
    return this.http.post(this.url + "/save-family-plan/" + isToggleTriggered, personalQuestions);
  }
}
