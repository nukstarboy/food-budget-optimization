import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {PersonalQuestions} from "../../models/personal-questions";

@Component({
  selector: 'app-family-questions',
  templateUrl: './family-questions.component.html',
  styleUrls: ['./family-questions.component.scss']
})
export class FamilyQuestionsComponent implements OnInit {
  @ViewChild('focus')
  child: ElementRef;

  @Input()
  public isSelectionDisabled: boolean = false;

  @Output()
  public buttonChange: EventEmitter<void> = new EventEmitter<void>();

  public familyQuestionsFormGroup: FormGroup = new FormGroup<any>({});
  public familyPackage: PersonalQuestions[] = [];
  public memberNumber: number = 1;

  constructor() { }

  ngOnInit(): void {
    this.initializeFormGroup();
  }

  public onDoneClick(): void {
    this.familyPackage = [...this.familyPackage, this.familyQuestionsFormGroup.value];
    this.memberNumber++;
    // this.child.nativeElement.focus();
    this.child.nativeElement.childNodes[1].focus()
    console.log(this.child.nativeElement.childNodes[1]);
    this.familyQuestionsFormGroup.reset();
    this.buttonChange.emit();
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
