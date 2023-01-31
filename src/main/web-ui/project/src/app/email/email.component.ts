import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {PersonalQuestionsService} from "../service/personal-questions.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  public emailFormGroup: FormGroup;

  public constructor(private readonly personalQuestionsService: PersonalQuestionsService,
                     private readonly router: Router) {
  }

  ngOnInit(): void {
    this.initializeFormGroup();
    this.personalQuestionsService.formArray$.subscribe((formGroup) => {
      console.log(formGroup);
    })
  }

  private initializeFormGroup(): void {
    this.emailFormGroup = new FormGroup<any>({
      email: new FormControl()
    })
  }

  public onButtonClick() {
    //TODO should send mail with personal meal plan!
  }

  public onProfileButtonClick(): void {
    this.router.navigate(['/profile']);
  }
}
