import {Component, OnInit} from '@angular/core';
import {AdminService} from "./service/admin-service.service";
import {Router} from "@angular/router";
import {HomePageService} from "./service/home-page.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
  public isUserLoggedIn: boolean = false;

  public constructor(private readonly adminService: AdminService,
                     private readonly router: Router,
                     private readonly emit: HomePageService) {
  }

  public ngOnInit(): void {
    this.emit.value$.subscribe((value) => {
      if (value === 'login') {
        this.isUserLoggedIn = this.adminService.isLoggedIn();
      } else if (value === 'logout'){
        this.isUserLoggedIn = false;
        this.adminService.logout();
      }
      this.isUserLoggedIn = this.adminService.isLoggedIn();
    })
  }

  public onHomeAnchorClick(): void {
    this.router.navigate(['/']);
  }

  public onProfileAnchorClick(): void {
    this.router.navigate(['/profile']);
  }

  public onLogoutAnchorClick(): void {
    this.emit.emitValue('logout')
  }

  public onLoginAnchorClick(): void {
    this.router.navigate(['/login']);
  }

  public onHelpAnchorClick(): void {
    this.router.navigate(['/help']);
  }
}
