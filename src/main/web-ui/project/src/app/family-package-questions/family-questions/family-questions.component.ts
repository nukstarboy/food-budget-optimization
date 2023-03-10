import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonalQuestions} from "../../models/personal-questions";
import {Router} from "@angular/router";

@Component({
  selector: 'app-family-questions',
  templateUrl: './family-questions.component.html',
  styleUrls: ['./family-questions.component.scss']
})
export class FamilyQuestionsComponent implements OnInit {
  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public memberNumber: number = 1;
  public selectedMembers: number;

  public constructor(private router: Router) {
  }

  ngOnInit(): void {
    this.initializeFormGroup();
    if (localStorage.getItem('onMember') !== null) {
      this.memberNumber = Number(localStorage.getItem('onMember'));
    }
    this.selectedMembers = Number(localStorage.getItem('selectedMembers'));
  }

  public onDoneClick(): void {
    const familyQuestions = this.buildFamilyQuestions();
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
        memberValues = [ ...memberValues, localStorage.getItem(getKey) ]
        localStorage.removeItem(getKey);
      }
      localStorage.removeItem('onMember');
      localStorage.removeItem('selectedMembers');
      this.router.navigate(['/']);
    }
  }

  private buildFamilyQuestions(): PersonalQuestions {
    const emailId = localStorage.getItem("username");
    return {
      gender: this.familyQuestionsFormGroup.controls['gender'].value,
      weight: this.familyQuestionsFormGroup.controls['weight'].value,
      age: this.familyQuestionsFormGroup.controls['age'].value,
      height: this.familyQuestionsFormGroup.controls['height'].value,
      bodyType: this.familyQuestionsFormGroup.controls['bodyType'].value,
      activity: this.familyQuestionsFormGroup.controls['activity'].value,
      workout: this.familyQuestionsFormGroup.controls['workout'].value,
      dietaryRestrictions: this.familyQuestionsFormGroup.controls['dietaryRestrictions'].value,
      planPeriod: this.familyQuestionsFormGroup.controls['planPeriod'].value,
      planOwner: emailId
    }
  }

  private initializeFormGroup(): void {
    this.familyQuestionsFormGroup = new FormGroup<any>({
      gender: new FormControl(['', Validators.required]),
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
