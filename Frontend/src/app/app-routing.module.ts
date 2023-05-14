import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddCustomerAccountComponent } from './customer-account/add-customer-account/add-customer-account.component';
import { CustomerAccountListComponent } from './customer-account/customer-account-list/customer-account-list.component';
import { UpdateCustomerAccountComponent } from './customer-account/update-customer-account/update-customer-account.component';
import { CustomerByAccountNumberComponent } from './customer/customer-by-account-number/customer-by-account-number.component';
import { CustomerListComponent } from './customer/customer-list/customer-list.component';
import { CustomerDetailByCifidComponent } from './customer/customer-detail-by-cifid/customer-detail-by-cifid.component';

const routes: Routes = [
{path: 'customer-list',title: 'Customer List',component: CustomerListComponent},
{path:'customer-account-list/:customerId',title: 'Customer Account List',component: CustomerAccountListComponent},
{path: 'customer-by-account-number/:accountNumber',title: 'Customer Details By Account Number',component: CustomerByAccountNumberComponent},
{path: 'customer-detail-by-cifid/:cifId',title: 'Customer Details By Customer Information Id',component: CustomerDetailByCifidComponent}

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
