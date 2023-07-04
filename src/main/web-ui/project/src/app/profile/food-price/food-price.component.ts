import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-food-price',
  templateUrl: './food-price.component.html',
  styleUrls: ['./food-price.component.scss']
})
export class FoodPriceComponent implements OnInit, AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public optimalPriceHeader: string;
  public displayedColumns: string[] = ['food', 'optimal price', 'owner', 'plan period'];

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
      return  'Budget quotidien';
    } else if (planPeriod == 'Weekly') {
      return  'Budget hebdomadaire';
    }
    return  'Budget annuel';
  }
}
