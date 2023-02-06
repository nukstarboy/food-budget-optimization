import {AfterViewInit, Component, Input, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-solve-time',
  templateUrl: './solve-time.component.html',
  styleUrls: ['./solve-time.component.scss']
})
export class SolveTimeComponent implements AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public displayedColumns: string[] = ['id', 'optimal annual price', 'problem solved time', 'problem solved iterations', 'owner'];

  constructor() { }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }
}
