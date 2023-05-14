import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { ActivatedRoute, Router } from '@angular/router';
import { AddCustomerComponent } from '../customer/add-customer/add-customer.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent {
  accountNumber: any;
  cifId: any;


  constructor(public activatedRoute: ActivatedRoute, private router: Router,public dialog: MatDialog ){

  }
    openAddCustomer(): void {
   this.dialog.open(AddCustomerComponent,{
    width:'50%',
    height:'100%'

   });
   
  }

   redirectByAccNum =  () =>{
    this.router.navigate(["/customer-by-account-number/"+this.accountNumber])
    
}

}