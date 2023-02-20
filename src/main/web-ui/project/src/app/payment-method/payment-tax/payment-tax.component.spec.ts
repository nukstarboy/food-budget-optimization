import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PaymentTaxComponent} from './payment-tax.component';

describe('PaymentTaxComponent', () => {
  let component: PaymentTaxComponent;
  let fixture: ComponentFixture<PaymentTaxComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PaymentTaxComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentTaxComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
