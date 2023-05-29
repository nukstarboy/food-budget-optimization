import {Component, Input} from '@angular/core';
import {FoodPrices} from "../../models/food-prices";
import {MatTableDataSource} from "@angular/material/table";
import {NutrientsQuantity} from "../../models/nutrients-quantity";
import {TaskDetails} from "../../models/task-details";

@Component({
  selector: 'app-personal-food',
  templateUrl: './personal-food.component.html',
  styleUrls: ['./personal-food.component.scss']
})
export class PersonalFoodComponent {

  @Input()
  public isStillTrialTime: boolean = false;

  @Input()
  public foodPriceDataSource = new MatTableDataSource<FoodPrices>();

  @Input()
  public nutrientsQuantityDataSource = new MatTableDataSource<NutrientsQuantity>();

  @Input()
  public taskDetailsDataSource = new MatTableDataSource<TaskDetails>();

}
