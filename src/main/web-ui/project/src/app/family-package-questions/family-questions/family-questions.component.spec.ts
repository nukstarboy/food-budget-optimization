import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FamilyQuestionsComponent} from './family-questions.component';

describe('FamilyQuestionsComponent', () => {
  let component: FamilyQuestionsComponent;
  let fixture: ComponentFixture<FamilyQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
