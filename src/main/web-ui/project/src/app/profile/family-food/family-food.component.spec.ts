import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FamilyFoodComponent} from './family-food.component';

describe('FamilyFoodComponent', () => {
  let component: FamilyFoodComponent;
  let fixture: ComponentFixture<FamilyFoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyFoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyFoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
