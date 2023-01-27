import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {AdminDetail} from '../models/admin-details';
import {AdminService} from '../service/admin-service.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  private adminDetail: AdminDetail | undefined;

  constructor(private readonly adminService: AdminService, private readonly router: Router) {
  }

  ngOnInit() {
    if ((this.adminService.isLoggedIn())) {
      this.router.navigate(['/profile', localStorage.getItem('id')]);
    } else {
      this.router.navigate(['/login']);
    }
  }

  // create the form object.
  form = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  get Email() {
    return this.form.get('email');
  }

  get Password() {
    return this.form.get('password');
  }

  onLoginButtonClick() {
    this.adminDetail = {
      name: undefined,
      emailId: this.form.controls.email.value!,
      password: this.form.controls.password.value!,
      role: undefined
    }

    this.adminService.login(this.adminDetail).subscribe((response) => {
        let result = response.json();

        if (result > 0) {
          let token = response.headers.get("Authorization");

          localStorage.setItem("token", token);
          localStorage.setItem("id", result);

          this.router.navigate(['/profile', result]);
        }
        if (result == -1) {
          alert("please register before login Or Invalid combination of Email and password");
        }

      },
      () => {
        console.log("Error in authentication");
      }
    );
  }

  onSignupButtonClick() {
    this.router.navigate(['/signup']);
  }
}
