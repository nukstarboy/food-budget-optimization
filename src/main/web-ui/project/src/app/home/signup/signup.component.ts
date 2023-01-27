import {Component} from '@angular/core';
import {AdminService} from "../../service/admin-service.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminDetail} from "../../models/admin-details";

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.scss']
})
export class SignupComponent {

  private adminDetail: AdminDetail | undefined;

  public constructor(private adminService: AdminService, private router: Router) {}

  // create the form object.
  form = new FormGroup({
    fullName: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
    confirmPassword: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
  });

  get FullName() {
    return this.form.get('fullName');
  }

  get Email() {
    return this.form.get('email');
  }

  get Password() {
    return this.form.get('password');
  }

  get ConfirmPassword() {
    return this.form.get('confirmPassword');
  }

  get Role() {
    return this.form.get('role');
  }


  onSaveButtonClick(): void {
    let pass = this.form.controls.password.value;
    let confirmPass = this.form.controls.confirmPassword.value;

    if (pass == confirmPass) {
      this.adminDetail = {
        name: this.form.controls.fullName.value!,
        emailId: this.form.controls.email.value!,
        password: this.form.controls.password.value!,
        role: this.form.controls.role.value!
      }

      this.adminService.saveAdminDetails(this.adminDetail).subscribe(
        () => {
          this.router.navigate(['/login']);
        },
        () => {
          alert("error occur while registring User. please try after sometime.")
        }
      );

    } else {
      alert("Password and confirm password not match.");
    }
  }
}
