import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-family-food-price',
  templateUrl: './family-food-price.component.html',
  styleUrls: ['./family-food-price.component.scss']
})
export class FamilyFoodPriceComponent implements OnInit, AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public optimalPriceHeader: string;
  public displayedColumns: string[] = ['food', 'optimal price', 'member name'];

  constructor() {
  }

  public ngOnInit(): void {
    this.optimalPriceHeader = this.getPlanPeriod();
  }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  private getPlanPeriod(): string {
    const planPeriod = this.dataSource.data[0].planPeriod;
    if (planPeriod == 'Daily') {
      return  'Optimal Daily Price';
    } else if (planPeriod == 'Weekly') {
      return  'Optimal Weekly Price';
    }
    return  'Optimal Yearly Price';
  }
}
