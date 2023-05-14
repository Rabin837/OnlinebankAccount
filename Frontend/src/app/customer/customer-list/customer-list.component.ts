import { Component, ViewChild } from '@angular/core';
import {  PageEvent } from '@angular/material/paginator';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from 'src/app/services/customer.service';
import { PaginationData } from 'src/app/class/pagination-data';
import { MatDialog } from '@angular/material/dialog';
import { UpdateCustomerComponent } from '../update-customer/update-customer.component';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
  
})


export class CustomerListComponent { 
  @ViewChild(MatSort) matSort! : MatSort;

   pageNumber: number;
   pageSize: number;
   totalCustomerNumber: number;
  customers: any;
  dataSource:MatTableDataSource<any>;
  displayedColumns = ["serialNumber","name","phone","email","nationality","cifId","branch","account","customerDetail"];
  responseMessage: any;
  constructor(private customerService: CustomerService, 
    public activatedRoute: ActivatedRoute, public dialog: MatDialog,private router: Router){    
  }
  ngOnInit(): void {
    this.pageNumber=0;
   this.pageSize=10;

   this.getCustomers(); 
   
  }
  openUpdateCustomer(customerId:any): void {
    this.dialog.open(UpdateCustomerComponent,{
     width:'50%',
     height:'100%',
data:{custmerid: customerId}
 
    });
    
   }
  getCustomers(){
    this.customerService.getAllCustomer(this.pageNumber,this.pageSize).subscribe(
      {
        next: (response: PaginationData[]) => {   

         if(response['message']!=null){
          this.responseMessage=response['message'];
        }
        else{

        this.customers=response['customer'];   
        this.dataSource = new MatTableDataSource(this.customers);//for filter
        this.dataSource.sort=this.matSort;//for sort

              this.totalCustomerNumber=response['totalCustomerNumber'],
        this.pageNumber=response['currentPageNumber']

        }
        },
        error: (error) => {
          alert('Error occor : '+error['message'])
        },
        complete: () => {
          
        }
      }
    );
  }

  handlePageEvent(event: PageEvent){
    this.pageNumber=event.pageIndex;
    this.pageSize=event.pageSize;
    this.getCustomers();
  }


  deleteCustomer(customerId: string){
    alert('Deletion of customer is not good practice')
    /*
    this.customerService.deleteOneCustomer(customerId).subscribe(
      {
        next: (response) => {
        
         alert('Response from api : '+response['message'])
        },
        error: (error) => {
          alert('Error occor : '+error['message'])
        },
        complete: () => {
          
        }
      }
    );
    
  
  */
}

filter(e: Event){
  const value=(e.target as  HTMLInputElement).value;
  this.dataSource.filter = value.trim().toLowerCase();
}
redirectByCifId(cifId: any){
  if(cifId==null){
  alert("Customer information id can't be empty")
}
else{
  this.router.navigate(["/customer-detail-by-cifid/"+cifId])

}
}
}