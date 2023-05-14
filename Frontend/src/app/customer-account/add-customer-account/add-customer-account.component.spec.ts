import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCustomerAccountComponent } from './add-customer-account.component';

describe('AddCustomerAccountComponent', () => {
  let component: AddCustomerAccountComponent;
  let fixture: ComponentFixture<AddCustomerAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCustomerAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCustomerAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
