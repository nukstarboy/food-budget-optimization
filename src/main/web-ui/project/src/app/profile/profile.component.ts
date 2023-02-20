import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {AdminService} from "../service/admin-service.service";
import {FoodPriceService} from "../service/food-price.service";
import {FoodPrices} from "../models/food-prices";
import {MatTableDataSource} from "@angular/material/table";
import {NutrientsQuantityService} from "../service/nutrients-quantity.service";
import {NutrientsQuantity} from "../models/nutrients-quantity";
import {TaskSolveService} from "../service/task-solve.service";
import {TaskDetails} from "../models/task-details";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public allFoodsWithPricesForLoginInOwner: FoodPrices[] = [];
  public allNutrientsForLoginInOwner: NutrientsQuantity[] = [];
  public taskDetailsForLoginInOwner: TaskDetails[] = [];
  public isStillTrialTime: boolean = false;
  public foodPriceDataSource = new MatTableDataSource<FoodPrices>();
  public nutrientsQuantityDataSource = new MatTableDataSource<NutrientsQuantity>();
  public taskDetailsDataSource = new MatTableDataSource<TaskDetails>();
  public dueOn: string;

  public constructor(private readonly adminService: AdminService,
                     private readonly foodPriceService: FoodPriceService,
                     private readonly nutrientsQuantityService: NutrientsQuantityService,
                     private readonly taskSolveService: TaskSolveService,
                     private router: Router) {
  }

  public ngOnInit(): void {
    if (this.adminService.isLoggedIn()) {
      const emailId = this.adminService.getLoginInUserEmailId()!;
      this.adminService.getLoggedInUser(emailId).subscribe((response) => {
        this.isStillTrialTime = new Date(response.dueOn) > new Date(Date.now());
        this.dueOn = response.dueOn;
        this.buildFoodPrice(emailId);
        this.buildNutrientsQuantity(emailId);
        this.buildTaskSolver(emailId);
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  public onNewMealDietButtonClick(): void {
    this.router.navigate(['/quiz/questions']);
  }

  private buildFoodPrice(emailId: string) {
    this.foodPriceService.getFoodPrices(emailId).subscribe((foodPrices: FoodPrices[]) => {
      this.allFoodsWithPricesForLoginInOwner = [...foodPrices];
      this.foodPriceDataSource.data = this.allFoodsWithPricesForLoginInOwner;
    });
  }

  private buildNutrientsQuantity(emailId: string) {
    this.nutrientsQuantityService.getNutrients(emailId).subscribe((nutrients) => {
      this.allNutrientsForLoginInOwner = [...nutrients];
      this.nutrientsQuantityDataSource.data = this.allNutrientsForLoginInOwner;
    });
  }

  private buildTaskSolver(emailId: string) {
    this.taskSolveService.getTaskSolve(emailId).subscribe((taskDetails) => {
      this.taskDetailsForLoginInOwner = [...taskDetails];
      this.taskDetailsDataSource.data = this.taskDetailsForLoginInOwner;
    });
  }
}
