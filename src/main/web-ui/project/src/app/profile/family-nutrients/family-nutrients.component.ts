import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-family-nutrients',
  templateUrl: './family-nutrients.component.html',
  styleUrls: ['./family-nutrients.component.scss']
})
export class FamilyNutrientsComponent implements AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public displayedColumns: string[] = ['nutrient name', 'normal quantity per day', 'minimum quantity per day', 'member name'];

  constructor() {
  }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
}
