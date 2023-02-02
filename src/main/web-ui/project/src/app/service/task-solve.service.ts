import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class TaskSolveService {
  private url: string = 'http://localhost:8080/task-solve';

  public constructor(private readonly http: HttpClient) {
  }

  public getTaskSolve(owner: string): Observable<any> {
    return this.http.post(this.url, owner);
  }
}
