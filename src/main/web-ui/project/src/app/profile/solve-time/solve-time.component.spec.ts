import {ComponentFixture, TestBed} from '@angular/core/testing';

import {SolveTimeComponent} from './solve-time.component';

describe('SolveTimeComponent', () => {
  let component: SolveTimeComponent;
  let fixture: ComponentFixture<SolveTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SolveTimeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SolveTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
