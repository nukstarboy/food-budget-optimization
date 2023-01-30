import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../service/admin-service.service";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public adminId: any;
  public data = [];

  public constructor(private adminService: AdminService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    if (this.adminService.isLoggedIn()) {
      this.route.paramMap.subscribe(params => {
        this.adminId = +params.get('adminId')!;
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  public onLogoutAnchorClick(): void {
    this.adminService.logout()
  }
}
