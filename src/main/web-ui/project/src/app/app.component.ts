import {Component, OnInit} from '@angular/core';
import {AdminService} from "./service/admin-service.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public isLoginButtonVisible: boolean = false;

  public constructor(private readonly adminService: AdminService) {
  }

  public ngOnInit(): void {
    this.isLoginButtonVisible = this.adminService.isLoggedIn();
  }

  public onLogoutAnchorClick(): void {
    this.adminService.logout()
  }
}
