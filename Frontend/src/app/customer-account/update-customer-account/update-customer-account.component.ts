import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerAccountService } from 'src/app/services/customer-account.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';


@Component({
  selector: 'app-update-customer-account',
  templateUrl: './update-customer-account.component.html',
  styleUrls: ['./update-customer-account.component.css']
})
export class UpdateCustomerAccountComponent implements OnInit{
  customerId: any;
  customerAccountId: any;
  customerAccount: any={};//for getting single object i.e. customer
  customerAccountUpdateForm=new FormGroup({
    accountType: new FormControl('',[Validators.required]),
currency: new FormControl('',[Validators.required]),    
currentBalance: new FormControl('',[Validators.required,Validators.min(1000),Validators.max(1000000000)]),
accountNumber: new FormControl('',[Validators.required])
  });
  
  constructor(private customerAccountService: CustomerAccountService,private router: Router, @Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<UpdateCustomerAccountComponent>){
    this.customerId = data.custmerid;

    this.customerAccountId = data.customeraccountid;

  }
  ngOnInit(): void {
    this.getCustomerSpecificAccount(); 
   

  }
  getCustomerSpecificAccount() {
  

    this.customerAccountService.getSpecificAccount(this.customerId,this.customerAccountId).subscribe(
       {
        next: (response) => {
          if(response['message']==null){
          this.customerAccount=response;
          this.customerAccountUpdateForm.get('accountType')?.setValue(this.customerAccount.accountType);
          this.customerAccountUpdateForm.get('currency')?.setValue(this.customerAccount.currency);     
          this.customerAccountUpdateForm.get('currentBalance')?.setValue(this.customerAccount.currentBalance); 
          this.customerAccountUpdateForm.get('accountNumber')?.setValue(this.customerAccount.accountNumber);      
     
      }
        else{
          alert('Response from Api : '+response['message'])
        }        
        },
        error: (error) => {
          alert('Error occor : '+error['message'])
        },
        complete: () => {
          
        }
      });
  
    }
   
onUpdate(customerId : any,customerAccountId: any){

  if(this.customerAccountUpdateForm.valid){
this.customerAccountService.updateAccount(customerId,customerAccountId,this.customerAccountUpdateForm.value).subscribe(
  {
    next: (response) => {        
     alert('Response from api : '+response['message'])
     if(response['message'] === "Customer account updated successfully"){
      this.dialogRef.close([]);
      this.reloadCurrentRoute()
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
  else{
    alert('please fill required field') 

  }
}
reloadCurrentRoute() {
  let currentUrl = this.router.url;
  this.router.navigateByUrl('/', {skipLocationChange: true}).then(()  =>{
      this.router.navigate([currentUrl]);
  });
}
}
