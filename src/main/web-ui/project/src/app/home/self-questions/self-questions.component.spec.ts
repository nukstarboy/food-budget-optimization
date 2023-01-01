import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SelfQuestionsComponent} from './self-questions.component';

describe('SelfQuestionsComponent', () => {
  let component: SelfQuestionsComponent;
  let fixture: ComponentFixture<SelfQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SelfQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelfQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
