import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonalQuestions} from "../models/personal-questions";

@Component({
  selector: 'app-family-package-questions',
  templateUrl: './family-package-questions.component.html',
  styleUrls: ['./family-package-questions.component.scss']
})
export class FamilyPackageQuestionsComponent implements OnInit {
  public array: number[] = [];
  public familyPackage: PersonalQuestions[] = [];

  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public isSelectionDisabled: boolean = false;
  public isButtonDisabled: boolean = false;

  constructor() {
  }

  public ngOnInit(): void {
    this.initializeFormGroup();
  }

  public onValueChange(value: number) {
    this.array = [];
    for (let i = 0; i < value; i++) {
      this.array = [...this.array, i];
    }
    this.isSelectionDisabled = true;
  }

  onDoneClick() {
    this.familyPackage = [...this.familyPackage, this.familyQuestionsFormGroup.value];
    this.isButtonDisabled = true;
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
