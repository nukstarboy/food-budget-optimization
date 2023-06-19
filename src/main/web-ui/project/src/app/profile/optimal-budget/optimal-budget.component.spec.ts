import {ComponentFixture, TestBed} from '@angular/core/testing';

import {OptimalBudgetComponent} from './optimal-budget.component';

describe('SolveTimeComponent', () => {
  let component: OptimalBudgetComponent;
  let fixture: ComponentFixture<OptimalBudgetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OptimalBudgetComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OptimalBudgetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
