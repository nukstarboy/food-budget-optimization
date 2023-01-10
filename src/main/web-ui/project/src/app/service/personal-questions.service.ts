import {Injectable} from "@angular/core";
import {BehaviorSubject} from "rxjs";

@Injectable()
export class PersonalQuestionsService {
  private _formGroupSource = new BehaviorSubject<any>(null);
  public formArray$ = this._formGroupSource.asObservable();

  public emitFormGroup(text: any) {
    if (!text) {
      return
    }

    this._formGroupSource.next(text)
  }

}
