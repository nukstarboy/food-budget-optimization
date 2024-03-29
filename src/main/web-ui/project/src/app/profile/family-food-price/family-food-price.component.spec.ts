import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FamilyFoodPriceComponent} from "./family-food-price.component";

describe('FoodPriceComponent', () => {
  let component: FamilyFoodPriceComponent;
  let fixture: ComponentFixture<FamilyFoodPriceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyFoodPriceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyFoodPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
