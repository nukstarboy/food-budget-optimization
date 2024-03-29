import {Component} from '@angular/core';
import {AdminService} from "../service/admin-service.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminDetail} from "../models/admin-details";
import {MatCheckboxChange} from '@angular/material/checkbox';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {
  private adminDetail: AdminDetail;

  public formGroup: FormGroup = new FormGroup({
    fullName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required)
  });
  public isPasswordVisible: boolean = true;
  public isCheckboxChecked: boolean = false;

  public constructor(private adminService: AdminService, private router: Router) {
  }

  public onSaveButtonClick(): void {
    let pass = this.formGroup.controls['password'].value;
    let confirmPass = this.formGroup.controls['confirmPassword'].value;

    if (pass == confirmPass) {
      this.buildAdminDetail();

      this.adminService.saveAdminDetails(this.adminDetail!).subscribe(
        () => {
          this.router.navigate(['/login']);
          alert("You got 7 days right trial period")
        },
        () => {
          alert("error occur while registring User. please try after sometime.")
        }
      );

    } else {
      alert("Password and confirm password not match.");
    }
  }

  private buildAdminDetail(): void {
    const sevenDaysAgo = new Date(Date.now() + 7 * 24 * 60 * 60 * 1000);
    this.adminDetail = {
      name: this.formGroup.controls['fullName'].value,
      emailId: this.formGroup.controls['email'].value,
      password: this.formGroup.controls['password'].value,
      dueOn: sevenDaysAgo.toLocaleDateString(),
      role: this.formGroup.controls['role'].value
    }
  }

  public onCheckboxStateChange(matCheckboxChange: MatCheckboxChange): void {
    this.isCheckboxChecked = matCheckboxChange.checked;
  }
}
