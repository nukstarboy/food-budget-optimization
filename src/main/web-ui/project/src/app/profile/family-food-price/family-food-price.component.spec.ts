import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FoodPriceComponent} from './family-food-price.component';

describe('FoodPriceComponent', () => {
  let component: FoodPriceComponent;
  let fixture: ComponentFixture<FoodPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FoodPriceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoodPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
