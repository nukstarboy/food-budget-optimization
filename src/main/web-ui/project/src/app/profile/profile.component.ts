import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {FoodPriceService} from "../service/food-price.service";
import {FoodPrices} from "../models/food-prices";
import {MatTableDataSource} from "@angular/material/table";
import {NutrientsQuantityService} from "../service/nutrients-quantity.service";
import {NutrientsQuantity} from "../models/nutrients-quantity";
import {TaskSolveService} from "../service/task-solve.service";
import {TaskDetails} from "../models/task-details";
import {AdminService} from "../service/admin-service.service";
import {FamilyNutrientsQuantity} from "../models/family-nutrients-quantity";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {
  public isStillTrialTime: boolean = false;
  public foodPriceDataSource = new MatTableDataSource<FoodPrices>([]);
  public familyFoodPriceDataSource = new MatTableDataSource<FoodPrices>([]);
  public nutrientsQuantityDataSource = new MatTableDataSource<NutrientsQuantity>([]);
  public familyNutrientsQuantityDataSource = new MatTableDataSource<FamilyNutrientsQuantity>([]);
  public taskDetailsDataSource = new MatTableDataSource<TaskDetails>([]);
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
      this.adminService.getLoggedInUser(emailId).subscribe((response: any) => {
        this.isStillTrialTime = new Date(response.dueOn) > new Date(Date.now());
        this.dueOn = response.dueOn;
        this.buildPersonalFoodPrice(emailId);
        this.buildNutrientsQuantity(emailId);
        this.buildTaskSolver(emailId);
        this.buildFamilyFoodPrice(response.familyMembers);
        this.buildFamilyNutrientsQuantity(response.familyMembers);
      });
    } else {
      this.router.navigate(['/login']);
    }
  }

  public onNewMealDietButtonClick(): void {
    this.router.navigate(['/quiz/questions']);
  }

  private buildPersonalFoodPrice(emailId: string) {
    this.foodPriceService.getFoodPrices(emailId).subscribe((foodPrices: FoodPrices[]) => {
      this.foodPriceDataSource.data = [...foodPrices];
    });
  }

  private buildNutrientsQuantity(emailId: string) {
    this.nutrientsQuantityService.getNutrients(emailId).subscribe((nutrients) => {
      this.nutrientsQuantityDataSource.data = [...nutrients];
    });
  }

  private buildTaskSolver(emailId: string) {
    this.taskSolveService.getTaskSolve(emailId).subscribe((taskDetails) => {
      this.taskDetailsDataSource.data = [...taskDetails];
    });
  }

  private buildFamilyFoodPrice(familyMembers: string) {
    this.foodPriceService.getFamilyFoodPricesByMembers(familyMembers).subscribe((foodPrices) => {
      this.familyFoodPriceDataSource.data = [...foodPrices];
    });
  }

  private buildFamilyNutrientsQuantity(familyMembers: string) {
    this.nutrientsQuantityService.getFamilyNutrients(familyMembers).subscribe((nutrients) => {
      this.familyNutrientsQuantityDataSource.data = [...nutrients];
    });
  }
}
