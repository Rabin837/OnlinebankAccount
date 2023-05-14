import { Component,  Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { CustomerAccountService } from 'src/app/services/customer-account.service';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';


@Component({
  selector: 'app-add-customer-account',
  templateUrl: './add-customer-account.component.html',
  styleUrls: ['./add-customer-account.component.css']
})
export class AddCustomerAccountComponent implements OnInit{
  customerId: any;
  customerAccountForm=new FormGroup({
    accountType: new FormControl('',Validators.required),
    currency: new FormControl('',[Validators.required]),
    currentBalance: new FormControl('',[Validators.required,Validators.min(1000),Validators.max(1000000000)]),
   
  });
 
  constructor(private customerAccountService: CustomerAccountService,private router: Router ,@Inject(MAT_DIALOG_DATA) public data: any,public dialogRef: MatDialogRef<AddCustomerAccountComponent>){
    this.customerId=data.customerid;
  }
  ngOnInit(): void {

  }
  onSubmit(){

    if(this.customerAccountForm.valid){
     

      this.customerAccountService.save(this.customerId ,this.customerAccountForm.value).subscribe( 
        {
          next: (response) => {        
           alert('Response from api : '+response['message'])         
           
           if(response['message'] === this.customerAccountForm.controls['accountType'].value+" account created successfully"){
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
