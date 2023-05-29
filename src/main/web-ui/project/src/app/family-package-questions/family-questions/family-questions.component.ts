import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PlanService} from "../../service/plan.service";
import {FamilyQuestions} from "../../models/family-questions";

@Component({
  selector: 'app-family-questions',
  templateUrl: './family-questions.component.html',
  styleUrls: ['./family-questions.component.scss']
})
export class FamilyQuestionsComponent implements OnInit {
  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public memberNumber: number = 1;
  public selectedMembers: number;

  public constructor(private router: Router,
                     private readonly planService: PlanService) {
  }

  ngOnInit(): void {
    this.initializeFormGroup();
    if (localStorage.getItem('onMember') !== null) {
      this.memberNumber = Number(localStorage.getItem('onMember'));
    }
    this.selectedMembers = Number(localStorage.getItem('selectedMembers'));
  }

  public onDoneClick(): void {
    if (localStorage.getItem('onMember') !== null) {
      this.memberNumber = Number(localStorage.getItem('onMember'));
    }
    if (this.selectedMembers > this.memberNumber) {
      const key = 'famMember' + Number(this.memberNumber)
      localStorage.setItem(key, JSON.stringify([this.familyQuestionsFormGroup.value]))
      this.memberNumber++;
      localStorage.setItem('onMember', String(this.memberNumber));
      this.familyQuestionsFormGroup.reset();
    } else {
      const key = 'famMember' + Number(this.memberNumber)
      localStorage.setItem(key, JSON.stringify([this.familyQuestionsFormGroup.value]))
      let memberValues: any[] = [];
      for (let i = 1; i <= this.memberNumber; i++) {
        const getKey = 'famMember' + i;
        memberValues = [...memberValues, localStorage.getItem(getKey)]
        localStorage.removeItem(getKey);
      }
      localStorage.removeItem('onMember');
      localStorage.removeItem('selectedMembers');
      const parsedMembers: FamilyQuestions[] = memberValues.flatMap((value) => {
        let member = JSON.parse(value);
        member[0].planOwner = localStorage.getItem('username');
        return member;
      });
      this.planService.saveFamilyPlan(parsedMembers).subscribe(() => {
      })
      this.router.navigate(['/']);
    }
  }

  private initializeFormGroup(): void {
    this.familyQuestionsFormGroup = new FormGroup<any>({
      gender: new FormControl(['', Validators.required]),
      memberName: new FormControl(['']),
      weight: new FormControl(['', Validators.required]),
      age: new FormControl(['', Validators.required]),
      height: new FormControl(['', Validators.required]),
      bodyType: new FormControl(['', Validators.required]),
      activity: new FormControl(['', Validators.required]),
      workout: new FormControl(['', Validators.required]),
      dietaryRestrictions: new FormControl(['', Validators.required]),
      planPeriod: new FormControl(['', Validators.required])
    });
  }
}
