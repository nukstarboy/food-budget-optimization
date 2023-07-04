import {AfterViewInit, Component, Input, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from "@angular/material/paginator";

@Component({
  selector: 'app-optimal-budget',
  templateUrl: './optimal-budget.component.html',
  styleUrls: ['./optimal-budget.component.scss']
})
export class OptimalBudgetComponent implements OnInit, AfterViewInit {
  @Input()
  public dataSource: any;

  @ViewChild(MatPaginator)
  public paginator: MatPaginator;

  public optimalPriceHeader: string;
  public displayedColumns: string[] = ['optimal price', 'member name', 'owner'];

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
