import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-food-price',
  templateUrl: './food-price.component.html',
  styleUrls: ['./food-price.component.scss']
})
export class FoodPriceComponent implements AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public displayedColumns: string[] = ['id', 'food', 'optimal annual price', 'owner'];

  constructor() { }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
}
