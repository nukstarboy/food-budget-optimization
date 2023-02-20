import {ComponentFixture, TestBed} from '@angular/core/testing';

import {HelpStaticComponent} from './help-static.component';

describe('HelpStaticComponent', () => {
  let component: HelpStaticComponent;
  let fixture: ComponentFixture<HelpStaticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HelpStaticComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HelpStaticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
