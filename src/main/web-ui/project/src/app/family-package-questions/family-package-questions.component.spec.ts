import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FamilyPackageQuestionsComponent} from './family-package-questions.component';

describe('FamilyPackageQuestionsComponent', () => {
  let component: FamilyPackageQuestionsComponent;
  let fixture: ComponentFixture<FamilyPackageQuestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FamilyPackageQuestionsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FamilyPackageQuestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
