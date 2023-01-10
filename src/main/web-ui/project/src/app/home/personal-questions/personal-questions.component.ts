import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PersonalQuestionsService} from "../../service/personal-questions.service";

@Component({
  selector: 'app-personal-questions',
  templateUrl: './personal-questions.component.html',
  styleUrls: ['./personal-questions.component.scss']
})
export class PersonalQuestionsComponent implements OnInit {
  public isLinear: boolean = true;

  public personalQuestionsFormGroup: FormGroup<any> = new FormGroup<any>({});

  public constructor(private readonly personalQuestionsService: PersonalQuestionsService,
                     private readonly router: Router) {
  }

  public ngOnInit(): void {
    this.initializeFormGroup();
  }

  public onDoneClick(): void {
    this.router.navigate(['/quiz/email']);
    this.personalQuestionsService.emitFormGroup(this.personalQuestionsFormGroup);
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
