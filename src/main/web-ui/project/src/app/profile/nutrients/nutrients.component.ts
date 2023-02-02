import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-nutrients',
  templateUrl: './nutrients.component.html',
  styleUrls: ['./nutrients.component.scss']
})
export class NutrientsComponent implements AfterViewInit {
  @Input()
  public dataSource: any;
  @Input()
  public isTableVisible: boolean;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public displayedColumns: string[] = ['id', 'nutrient name', 'normal quantity per day', 'minimum quantity per day', 'owner'];

  constructor() {
  }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
}
