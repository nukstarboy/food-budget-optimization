import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PersonalQuestionsComponent} from './personal-questions.component';

describe('PersonalQuestionsComponent', () => {
  let component: PersonalQuestionsComponent;
  let fixture: ComponentFixture<PersonalQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PersonalQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PersonalQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
