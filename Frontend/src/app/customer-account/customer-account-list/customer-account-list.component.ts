import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerAccount } from 'src/app/class/customer-account';
import { CustomerAccountService } from 'src/app/services/customer-account.service';
import { AddCustomerAccountComponent } from '../add-customer-account/add-customer-account.component';
import { UpdateCustomerAccountComponent } from '../update-customer-account/update-customer-account.component';

@Component({
  selector: 'app-customer-account-list',
  templateUrl: './customer-account-list.component.html',
  styleUrls: ['./customer-account-list.component.css'],
})
export class CustomerAccountListComponent implements OnInit {
  static ngOnInit() {
    throw new Error('Method not implemented.');
  }
  customerId: any;
  customerAccounts: CustomerAccount[]; //for getting array of object i.e. customer-accounts
  displayedColumns = [
    'name',
    'accountNumber',
    'accountType',
    'currentBalance',
    'accountOpenDate',
    'accountAction',
  ];
  responseMessage: any;

  constructor(
    private customerAccountService: CustomerAccountService,
    public activatedRoute: ActivatedRoute,public dialog: MatDialog 
  ) {}
  ngOnInit(): void {
    this.getCustomerAccounts();
  }
  openAddCustomerAccount(customerId: any): void {
    this.dialog.open(AddCustomerAccountComponent,{
     width:'50%',
     height:'29.4%',
     data:{customerid: customerId}
 
    });
    
   }
   openUpdateCustomerAccount(customerId:any,customerAccountId:any): void {
    this.dialog.open(UpdateCustomerAccountComponent,{
     width:'50%',
     height:'32%',
data:{custmerid: customerId,customeraccountid:customerAccountId}
 
    });
    
   }
  getCustomerAccounts() {
    this.customerId = this.activatedRoute.snapshot.params['customerId'];

    this.customerAccountService.getAccounts(this.customerId).subscribe({
      next: (response: CustomerAccount[]) => {
        if (response['message'] != null) {
          this.responseMessage = response['message'];
        } else {
          this.customerAccounts = response;
        }
      },
      error: (error) => {
        alert('Error occor : ' + error['message']);
      },
      complete: () => {},
    });
  }
  deleteCustomerAccount(){
    alert('Deletion of customer account is not good practice')
  }
}
