import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddCustomerComponent } from './customer/add-customer/add-customer.component';
import { UpdateCustomerComponent } from './customer/update-customer/update-customer.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material/material.module';
import { CustomerAccountListComponent } from './customer-account/customer-account-list/customer-account-list.component';
import { AddCustomerAccountComponent } from './customer-account/add-customer-account/add-customer-account.component';
import { UpdateCustomerAccountComponent } from './customer-account/update-customer-account/update-customer-account.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CustomerByAccountNumberComponent } from './customer/customer-by-account-number/customer-by-account-number.component';
import { CustomerDetailByCifidComponent } from './customer/customer-detail-by-cifid/customer-detail-by-cifid.component';
import { FullImageViewComponent } from './full-image-view/full-image-view.component';





@NgModule({
  declarations: [
    AppComponent,
    CustomerListComponent,
    AddCustomerComponent,
    UpdateCustomerComponent,
    CustomerAccountListComponent,
    AddCustomerAccountComponent,
    UpdateCustomerAccountComponent,
DashboardComponent,
CustomerByAccountNumberComponent,
CustomerDetailByCifidComponent,
FullImageViewComponent,
  ],
  imports: [
    
    BrowserModule,
    AppRoutingModule,
    FormsModule,ReactiveFormsModule,
    HttpClientModule, BrowserAnimationsModule,
    MaterialModule

   
          ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
