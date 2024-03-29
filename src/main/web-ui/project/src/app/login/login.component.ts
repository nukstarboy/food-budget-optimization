import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from "@angular/router";
import {AdminDetail} from '../models/admin-details';
import {AdminService} from '../service/admin-service.service';
import {HomePageService} from "../service/home-page.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {
  private adminDetail: AdminDetail;

  public isPasswordVisible: boolean = true;

  public formGroup: FormGroup = new FormGroup({
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required)
  });

  public constructor(private readonly adminService: AdminService,
                     private readonly router: Router,
                     private readonly emitState: HomePageService) {
  }

  public ngOnInit(): void {
    if (this.adminService.isLoggedIn()) {
      this.router.navigate(['/profile']);
    } else {
      this.router.navigate(['/login']);
    }
  }

  public onLoginButtonClick(): void {
    this.adminDetail = {
      emailId: this.formGroup.controls['email'].value,
      password: this.formGroup.controls['password'].value
    };

    this.adminService.login(this.adminDetail).subscribe((response) => {
      if (response.body.httpStatus === '200 OK') {
        let token = response.body.authenticationToken;
        localStorage.setItem("token", token);
        localStorage.setItem("username", this.adminDetail?.emailId!);

        this.emitState.emitValue('login');
        this.router.navigate(['/profile']);
      } else if (response.body.httpStatus === '404 NOT_FOUND') {
        alert("please register before login Or Invalid combination of Email and password");
      } else {
        console.log("Error in authentication");
      }
    });
  }

  public onSignupButtonClick(): void {
    this.router.navigate(['/signup']);
  }

}
