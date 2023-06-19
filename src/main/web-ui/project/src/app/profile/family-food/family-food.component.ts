import {Component, Input} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {FoodPrices} from "../../models/food-prices";
import {FamilyNutrientsQuantity} from "../../models/family-nutrients-quantity";
import {TaskDetails} from "../../models/task-details";

@Component({
  selector: 'app-family-food',
  templateUrl: './family-food.component.html',
  styleUrls: ['./family-food.component.scss']
})
export class FamilyFoodComponent {

  @Input()
  public isStillTrialTime: boolean = false;

  @Input()
  public foodPriceDataSource = new MatTableDataSource<FoodPrices>();

  @Input()
  public nutrientsQuantityDataSource = new MatTableDataSource<FamilyNutrientsQuantity>();

  @Input()
  public taskDetailsDataSource = new MatTableDataSource<TaskDetails>();

}
