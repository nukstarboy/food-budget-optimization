import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {Router} from "@angular/router";
import {PlanService} from "../../service/plan.service";
import {FamilyQuestions} from "../../models/family-questions";
import {MatStepper} from "@angular/material/stepper";

@Component({
  selector: 'app-family-questions',
  templateUrl: './family-questions.component.html',
  styleUrls: ['./family-questions.component.scss']
})
export class FamilyQuestionsComponent implements OnInit {
  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public memberNumber: number = 1;
  public selectedMembers: number;
  public isToggleForPersonalFoodTriggered: boolean;
  public savedPlans: any = [];

  public constructor(private router: Router,
                     private readonly planService: PlanService) {
  }

  ngOnInit(): void {
    this.initializeFormGroup();
    if (localStorage.getItem('onMember') !== null) {
      this.memberNumber = Number(localStorage.getItem('onMember'));
    }
    this.selectedMembers = Number(localStorage.getItem('selectedMembers'));
    this.isToggleForPersonalFoodTriggered = this.getToggleState();
    console.log(this.isToggleForPersonalFoodTriggered)
  }

  private getToggleState(): boolean {
    return localStorage.getItem('isToggleTriggered') === 'true';
  }

  public onDoneClick(stepper: MatStepper): void {
    //TODO Check if user is still logged in !!!
    this.savedPlans = [...this.savedPlans, this.familyQuestionsFormGroup.value];
    if (this.memberNumber == this.selectedMembers) {
      const parsedMembers: FamilyQuestions[] = this.savedPlans.flatMap((value: any) => {
        value.planOwner = localStorage.getItem('username');
        return value;
      });
      console.log(parsedMembers)
      this.planService.saveFamilyPlan(parsedMembers, this.isToggleForPersonalFoodTriggered).subscribe(() => {
          this.router.navigate(['/profile']);
        },
        (error) => {
          if (error.status === 500) {
            alert('The problem does not have an optimal solution! Please check your personal food!')
          } else {
            alert('Quelque chose s\'est mal passé !')
          }
          this.router.navigate(['/profile']);
        });
    } else {
      this.memberNumber++;
      stepper.reset();
    }
  }

  private initializeFormGroup(): void {
    this.familyQuestionsFormGroup = new FormGroup<any>({
      gender: new FormControl(['']),
      memberName: new FormControl(['']),
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
