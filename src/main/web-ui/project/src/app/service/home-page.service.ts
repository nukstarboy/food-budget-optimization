import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable()
export class HomePageService {
  private _source = new BehaviorSubject<any>(null);
  public value$ = this._source.asObservable();

  public emitValue(text: any) {
    if (!text) {
      return
    }

    this._source.next(text)
  }

}
