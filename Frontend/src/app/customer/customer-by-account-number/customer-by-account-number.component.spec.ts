import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerByAccountNumberComponent } from './customer-by-account-number.component';

describe('CustomerByAccountNumberComponent', () => {
  let component: CustomerByAccountNumberComponent;
  let fixture: ComponentFixture<CustomerByAccountNumberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerByAccountNumberComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerByAccountNumberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
