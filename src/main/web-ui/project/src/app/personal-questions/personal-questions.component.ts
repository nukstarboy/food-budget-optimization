import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {PersonalQuestions} from '../models/personal-questions';
import {PersonalQuestionsService} from "../service/personal-questions.service";
import {PlanService} from "../service/plan.service";
import {FoodNutrientsService} from "../service/food-nutrients.service";

@Component({
  selector: 'app-personal-questions',
  templateUrl: './personal-questions.component.html',
  styleUrls: ['./personal-questions.component.scss']
})
export class PersonalQuestionsComponent implements OnInit {
  public personalQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public isChecked: boolean = false;
  public doesUserHavePersonalFood: boolean = false;

  public constructor(private readonly personalQuestionsService: PersonalQuestionsService,
                     private readonly planService: PlanService,
                     private readonly router: Router,
                     private readonly foodNutrientsService: FoodNutrientsService) {
  }

  public ngOnInit(): void {
    this.initializeFormGroup();
    this.foodNutrientsService.existByOwner(localStorage.getItem('username')).subscribe((exists: boolean) => this.doesUserHavePersonalFood = exists)
  }

  public onDoneClick(): void {
    const personalQuestions = this.buildPersonalQuestions();
    this.planService.savePersonalPlan(personalQuestions, this.isChecked).subscribe(() => {
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
      planPeriod: this.personalQuestionsFormGroup.controls['planPeriod'].value,
      planOwner: emailId
    }
  }

  private initializeFormGroup(): void {
    this.personalQuestionsFormGroup = new FormGroup<any>({
      gender: new FormControl(['']),
      weight: new FormControl(['']),
      age: new FormControl(['']),
      height: new FormControl(['']),
      bodyType: new FormControl(['']),
      activity: new FormControl(['']),
      workout: new FormControl(['']),
      planPeriod: new FormControl([''])
    });
  }
}
