import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PersonalQuestionsService} from "../../service/personal-questions.service";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  public emailFormGroup: FormGroup<any> = new FormGroup<any>({});

  public constructor(private readonly personalQuestionsService: PersonalQuestionsService) {
  }

  ngOnInit(): void {
    this.initializeFormGroup();
    this.personalQuestionsService.formArray$.subscribe((formGroup) => {
      console.log(formGroup);
    })
  }

  private initializeFormGroup() {
    this.emailFormGroup = new FormGroup<any>({
      email: new FormControl()
    })
  }

  public onButtonClick() {

  }
}
