import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FamilyNutrientsComponent} from './family-nutrients.component';

describe('NutrientsComponent', () => {
  let component: FamilyNutrientsComponent;
  let fixture: ComponentFixture<FamilyNutrientsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyNutrientsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyNutrientsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
