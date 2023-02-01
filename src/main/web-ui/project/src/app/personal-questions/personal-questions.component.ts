import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PersonalQuestions} from '../models/personal-questions';
import {PersonalQuestionsService} from "../service/personal-questions.service";
import {PlanService} from "../service/plan.service";

@Component({
  selector: 'app-personal-questions',
  templateUrl: './personal-questions.component.html',
  styleUrls: ['./personal-questions.component.scss']
})
export class PersonalQuestionsComponent implements OnInit {
  public isStepperLinear: boolean = true;

  public personalQuestionsFormGroup: FormGroup<any> = new FormGroup<any>({});

  public constructor(private readonly personalQuestionsService: PersonalQuestionsService,
                     private readonly planService: PlanService,
                     private readonly router: Router) {
  }

  public ngOnInit(): void {
    this.initializeFormGroup();
  }

  public onDoneClick(): void {
    const personalQuestions = this.buildPersonalQuestions();
    this.planService.savePersonalPlan(personalQuestions).subscribe(() => {
      this.personalQuestionsService.emitFormGroup(this.personalQuestionsFormGroup);
      this.router.navigate(['/quiz/email']);
    });
  }

  private buildPersonalQuestions(): PersonalQuestions {
    const emailId = localStorage.getItem("username");
    return {
      gender: this.personalQuestionsFormGroup.controls['gender'].value,
      weight: this.personalQuestionsFormGroup.controls['weight'].value,
      age: this.personalQuestionsFormGroup.controls['age'].value,
      height: this.personalQuestionsFormGroup.controls['height'].value,
      bodyType: this.personalQuestionsFormGroup.controls['bodyType'].value,
      activity: this.personalQuestionsFormGroup.controls['activity'].value,
      workout: this.personalQuestionsFormGroup.controls['workout'].value,
      dietaryRestrictions: this.personalQuestionsFormGroup.controls['dietaryRestrictions'].value,
      planOwner: emailId
    }
  }

  private initializeFormGroup(): void {
    this.personalQuestionsFormGroup = new FormGroup<any>({
      gender: new FormControl(['', Validators.required]),
      weight: new FormControl(['', Validators.required]),
      age: new FormControl(['', Validators.required]),
      height: new FormControl(['', Validators.required]),
      bodyType: new FormControl(['', Validators.required]),
      activity: new FormControl(['', Validators.required]),
      workout: new FormControl(['', Validators.required]),
      dietaryRestrictions: new FormControl(['', Validators.required])
    });
  }
}
