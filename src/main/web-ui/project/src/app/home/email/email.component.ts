import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-email',
  templateUrl: './email.component.html',
  styleUrls: ['./email.component.scss']
})
export class EmailComponent implements OnInit {
  public emailFormGroup: FormGroup = this.formBuilder.group({
    email: ['', Validators.required]
  });

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.initializeFormGroup();
  }

  private initializeFormGroup() {

  }

  public onButtonClick() {

  }
}
