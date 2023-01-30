import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../service/admin-service.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  public constructor(private adminService: AdminService, private route: ActivatedRoute, private router: Router) {
  }

  public ngOnInit(): void {
    const isNotLoggedIn = !this.adminService.isLoggedIn();
    if (isNotLoggedIn) {
      this.router.navigate(['/login']);
    }
  }

  public onLogoutAnchorClick(): void {
    this.adminService.logout()
  }
}
