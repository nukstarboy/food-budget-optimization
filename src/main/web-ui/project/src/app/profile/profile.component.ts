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
  public haveData = 0;

  public data = [];

  public dataRequest = false;

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

  public getAdminData(): void {
    this.haveData = 0;

    this.dataRequest = true;

    // this.adminService.getAdminDetail(this.adminId).subscribe(
    //   response => {
    //
    //     let result = response.json();
    //     this.data = result;
    //
    //     if (result == " ") {
    //       this.haveData = 0;
    //     } else {
    //       this.haveData = this.haveData + 1;
    //     }
    //   },
    //   error => {
    //     console.log("error while getting Admin Data");
    //   }
    // );
  }

  public onLogoutAnchorClick(): void {
    this.adminService.logout()
  }
}
