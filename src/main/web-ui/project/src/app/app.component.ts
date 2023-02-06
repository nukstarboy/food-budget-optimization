import {Component, OnInit} from '@angular/core';
import {AdminService} from "./service/admin-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public isUserLoggedIn: boolean = false;

  public constructor(private readonly adminService: AdminService,
                     private readonly router: Router) {
  }

  public ngOnInit(): void {
    this.isUserLoggedIn = this.adminService.isLoggedIn();
  }

  public onHomeAnchorClick(): void {
    this.router.navigate(['/']);
  }

  public onProfileAnchorClick(): void {
    this.router.navigate(['/profile']);
  }

  public onLogoutAnchorClick(): void {
    this.adminService.logout();
    // window.location.reload();
  }
}
