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

  public constructor(private readonly adminService: AdminService, private readonly router: Router) {}

  public ngOnInit(): void {
    if ((this.adminService.isLoggedIn())) {
      this.router.navigate(['/profile']);
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

  public onLoginButtonClick(): void {
    this.adminDetail = {
      name: undefined,
      emailId: this.form.controls.email.value!,
      password: this.form.controls.password.value!,
      role: undefined
    }

    this.adminService.login(this.adminDetail).subscribe((response) => {
        if (response.status === 200) {
          //TODO It's not working...
          let token = response.headers.get("Authorization");
          localStorage.setItem("token", token);

          this.router.navigate(['/profile']);
        }
      },
      (response) => {
        if (response.status === 404) {
          alert("please register before login Or Invalid combination of Email and password");
        } else {
          console.log("Error in authentication");
        }
      }
    );
  }

  public onSignupButtonClick(): void {
    this.router.navigate(['/signup']);
  }
}
