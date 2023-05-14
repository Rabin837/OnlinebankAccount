import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCustomerAccountComponent } from './update-customer-account.component';

describe('UpdateCustomerAccountComponent', () => {
  let component: UpdateCustomerAccountComponent;
  let fixture: ComponentFixture<UpdateCustomerAccountComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UpdateCustomerAccountComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateCustomerAccountComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
