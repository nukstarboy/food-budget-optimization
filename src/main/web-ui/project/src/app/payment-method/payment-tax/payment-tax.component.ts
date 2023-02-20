import {Component, OnInit} from '@angular/core';
import {MatRadioChange} from "@angular/material/radio";

@Component({
  selector: 'app-payment-tax',
  templateUrl: './payment-tax.component.html',
  styleUrls: ['./payment-tax.component.scss']
})
export class PaymentTaxComponent implements OnInit {
  public plan: string;
  public openBankDetailsScreen: boolean = false;
  public isButtonDisabled: boolean = true;
  public isRadioGroupDisabled: boolean = false;

  constructor() { }

  ngOnInit(): void {
  }

  public onPlanChange(matRadioChange: MatRadioChange): void {
    this.plan = matRadioChange.value;
    this.isButtonDisabled = false;
  }

  public onPlanChoose(): void {
    this.openBankDetailsScreen = true;
    this.isRadioGroupDisabled = true;
    this.isButtonDisabled = true;
  }
}
