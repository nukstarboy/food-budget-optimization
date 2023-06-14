import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PersonalMeatCreatorComponent} from './personal-meat-creator.component';

describe('PersonalMeatCreatorComponent', () => {
  let component: PersonalMeatCreatorComponent;
  let fixture: ComponentFixture<PersonalMeatCreatorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalMeatCreatorComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalMeatCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
