import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable()
export class TestService {
  private url: string = 'http://localhost:8080/';

  public constructor(private readonly http: HttpClient) {
  }

  getString(): Observable<any> {
    return this.http.get(this.url, { responseType: "text" });
  }
}
