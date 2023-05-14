import { HttpParams } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { CustomerAccountService } from 'src/app/services/customer-account.service';

@Component({
  selector: 'app-customer-by-account-number',
  templateUrl: './customer-by-account-number.component.html',
  styleUrls: ['./customer-by-account-number.component.css']
})
export class CustomerByAccountNumberComponent implements OnInit {
  accountNumber: any;
  customerAccount: any={};
  citizenshipFrontImg: any;
  citizenshipBackImg: any;
  passportImg: any;
  profileImg: any;

  ngOnInit(): void {
    this.accountNumber = this.activatedRoute.snapshot.params['accountNumber'];

    this.getCustomerByAccNum();
  }
  constructor(private customerAccountService: CustomerAccountService,private activatedRoute: ActivatedRoute, private router: Router,private sanitizer: DomSanitizer){

  }
  getCustomerByAccNum(){
    let params = new HttpParams().set("accountNumber",this.accountNumber);
this.customerAccountService.getByAccountNumber(params).subscribe(
  {
   next: (response) => {
     if(response['message']==null){
       this.customerAccount=response;  

       this.citizenshipFrontImg=this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.customerAccount.customer.citizenshipFrontEncodedImage}`); 
       this.citizenshipBackImg=this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.customerAccount.customer.citizenshipBackEncodedImage}`); 
       this.passportImg=this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.customerAccount.customer.passportEncodedImage}`); 
       this.profileImg=this.sanitizer.bypassSecurityTrustResourceUrl(`data:image/jpg;base64, ${this.customerAccount.customer.profileEncodedImage}`); 

         
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

}
