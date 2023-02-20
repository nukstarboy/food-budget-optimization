import {Component, Input, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {AdminService} from "../../../service/admin-service.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-bank-details',
  templateUrl: './bank-details.component.html',
  styleUrls: ['./bank-details.component.scss']
})
export class BankDetailsComponent implements OnInit {
  @Input()
  public plan: string;

  public formGroup: FormGroup = new FormGroup({
    name: new FormControl('', Validators.required),
    iban: new FormControl('', Validators.required)
  });

  public constructor(private readonly adminService: AdminService,
                     private readonly route: Router) {
  }

  ngOnInit(): void {
    console.log(this.plan)
  }

  public onSubmitButtonClick(): void {
    const emailId = this.adminService.getLoginInUserEmailId()!;
    const dueOn = this.calculateDueOn();
    this.adminService.updateDueOn(emailId, dueOn).subscribe(() => {
      this.route.navigate(['/profile'])
    })
  }

  private calculateDueOn(): string {
    if (this.plan === 'weekly') {
      return new Date(Date.now() + 7 * 24 * 60 * 60 * 1000).toLocaleDateString();
    } else if (this.plan === 'monthly') {
      return new Date(Date.now() + 28 * 24 * 60 * 60 * 1000).toLocaleDateString();
    } else if (this.plan === 'yearly') {
      return new Date(Date.now() + 365 * 24 * 60 * 60 * 1000).toLocaleDateString();
    }
    return new Date(Date.now() + 9999 * 24 * 60 * 60 * 1000).toLocaleDateString();
  }
}
