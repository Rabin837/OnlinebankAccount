import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerDetailByCifidComponent } from './customer-detail-by-cifid.component';

describe('CustomerDetailByCifidComponent', () => {
  let component: CustomerDetailByCifidComponent;
  let fixture: ComponentFixture<CustomerDetailByCifidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerDetailByCifidComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CustomerDetailByCifidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
