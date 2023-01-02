import {Component} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-personal-questions',
  templateUrl: './personal-questions.component.html',
  styleUrls: ['./personal-questions.component.scss']
})
export class PersonalQuestionsComponent {
  public isLinear: boolean = true;

  public genderFormGroup = this.formBuilder.group({
    gender: ['', Validators.required],
  });
  public personalFormGroup = this.formBuilder.group({
    //TODO Should check the initial value of number ('' or 0)
    weight: ['', Validators.required],
    age: ['', Validators.required],
    height: ['', Validators.required],
  });
  public bodyTypeFormGroup: FormGroup = this.formBuilder.group({
    bodyType: ['', Validators.required]
  });
  public mealsFormGroup: FormGroup = this.formBuilder.group({
    meals: ['', Validators.required]
  });
  public workoutFormGroup: FormGroup = this.formBuilder.group({
    workout: ['', Validators.required]
  });
  public sleepFormGroup: FormGroup = this.formBuilder.group({
    sleep: ['', Validators.required]
  });
  public dietaryRestrictionsFormGroup: FormGroup = this.formBuilder.group({
    dietaryRestrictions: ['', Validators.required]
  });

  public constructor(private formBuilder: FormBuilder,
                     private readonly router: Router) {
  }

  public onDoneClick(): void {
    this.router.navigate(['/account/create']);
    console.log(this.genderFormGroup);
    console.log(this.personalFormGroup);
  }

}
