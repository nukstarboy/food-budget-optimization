import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-self-questions',
  templateUrl: './self-questions.component.html',
  styleUrls: ['./self-questions.component.scss']
})
export class SelfQuestionsComponent {
  public isLinear: boolean = true;

  public firstFormGroup = this.formBuilder.group({
    gender: ['', Validators.required],
  });
  public secondFormGroup = this.formBuilder.group({
    //TODO Should check the initial value of number ('' or 0)
    weight: ['', Validators.required],
    age: ['', Validators.required],
    height: ['', Validators.required],
  });
  public thirdFormGroup: FormGroup = this.formBuilder.group({
    weight: ['', Validators.required]
  });

  public constructor(private formBuilder: FormBuilder) {
  }

}
