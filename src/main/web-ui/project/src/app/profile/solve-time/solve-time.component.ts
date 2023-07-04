import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-solve-time',
  templateUrl: './solve-time.component.html',
  styleUrls: ['./solve-time.component.scss']
})
export class SolveTimeComponent implements OnInit, AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public optimalPriceHeader: string;
  public displayedColumns: string[] = ['optimal price', 'problem solved time', 'problem solved iterations', 'owner'];

  public ngOnInit(): void {
    this.optimalPriceHeader = this.getPlanPeriod();
  }

  public ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  private getPlanPeriod(): string {
    const planPeriod = this.dataSource.data[0].planPeriod;
    if (planPeriod == 'Daily') {
      return  'Prix journalier optimal';
    } else if (planPeriod == 'Weekly') {
      return  'Prix hebdomadaire optimal';
    }
    return  'Prix annuel optimal';
  }
}
