import {AfterViewInit, ChangeDetectorRef, Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonalQuestions} from "../models/personal-questions";
import {MatStepper} from "@angular/material/stepper";

@Component({
  selector: 'app-family-package-questions',
  templateUrl: './family-package-questions.component.html',
  styleUrls: ['./family-package-questions.component.scss']
})
export class FamilyPackageQuestionsComponent implements OnInit, AfterViewInit {
  @ViewChild('focusable')
  focusable: ElementRef;

  @ViewChild('stepper') public myStepper: MatStepper;

  public array: number[] = [];
  public familyPackage: PersonalQuestions[] = [];
  public memberNumber: number = 1;

  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public isSelectionDisabled: boolean = false;

  constructor(private cdRef: ChangeDetectorRef) {
  }

  ngAfterViewInit(): void {
    console.log(this.myStepper);
  }

  public ngOnInit(): void {
    this.initializeFormGroup();
  }

  public onValueChange(value: number) {
    this.isSelectionDisabled = true;
  }

  onDoneClick(stepper: MatStepper) {
    this.familyPackage = [...this.familyPackage, this.familyQuestionsFormGroup.value];
    this.memberNumber++;
    this.familyQuestionsFormGroup.reset();
    this.cdRef.detectChanges();
    this.focusable.nativeElement.focus();
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
