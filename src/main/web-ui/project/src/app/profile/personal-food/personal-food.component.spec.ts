import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PersonalFoodComponent} from './personal-food.component';

describe('PersonalFoodComponent', () => {
  let component: PersonalFoodComponent;
  let fixture: ComponentFixture<PersonalFoodComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalFoodComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalFoodComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
