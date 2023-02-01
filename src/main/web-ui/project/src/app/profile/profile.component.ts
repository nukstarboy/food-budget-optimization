import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AdminService} from "../service/admin-service.service";
import {FoodPriceService} from "../service/food-price.service";
import {FoodPrices} from "../models/food-prices";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public allFoodsWithPricesForLoginInOwner: FoodPrices[] = [];
  public dataSource = new MatTableDataSource<FoodPrices>();

  public constructor(private readonly adminService: AdminService,
                     private readonly foodPriceService: FoodPriceService,
                     private router: Router) {
  }

  public ngOnInit(): void {
    if (this.adminService.isLoggedIn()) {
      const emailId = this.adminService.getLoginInUserEmailId()!;
      this.foodPriceService.getFoodPrices(emailId).subscribe((foodPrices: FoodPrices[]) => {
        this.allFoodsWithPricesForLoginInOwner = [...foodPrices];
        this.dataSource.data = this.allFoodsWithPricesForLoginInOwner;
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  public onCreateNewMealDietButtonClick(): void {
    this.router.navigate(['/quiz/questions']);
  }

  public displayNutrientsInfo(): void {

  }

  public onCreateNewDietButtonClick(): void {

  }
}
