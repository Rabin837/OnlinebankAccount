import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Address } from 'src/app/class/address';
import { AddressService } from 'src/app/services/address.service';
import { CustomerService } from 'src/app/services/customer.service';

@Component({
  selector: 'app-add-customer',
  templateUrl: './add-customer.component.html',
  styleUrls: ['./add-customer.component.css'],
})
export class AddCustomerComponent implements OnInit {
  maxDate = new Date();
  showCitizenshipFields = false;
  showPassportFields = false;
  sameAsPermanntCheckBox = false; 
  
  submitted = false;

  public permanentAddress!: Address[];
  permanentCountries: any;
  permanentStates: any;
  permanentDistricts: any;
  permanentMunicipalities: any;
  public temporaryAddress!: Address[];
  temporaryCountries: any;
  temporaryStates: any;
  temporaryDistricts: any;
  temporaryMunicipalities: any;

  selectedCitizenshipFrontImg: any;
  citizenshipFrontEncodedImg: any;

  selectedCitizenshipBackImg: any;
  citizenshipBackEncodedImg: any;

  selectedPassportImg: any;
  passportEncodedImg: any;

  selectedProfileImg: any;
  profileEncodedImg: any;

  customerForm = new FormGroup({
    name: new FormControl('', Validators.required),
    dob: new FormControl('', [Validators.required]),
    gender: new FormControl('', [Validators.required]),
    phone: new FormControl('', [Validators.required, Validators.pattern('[0-9]{10}')]),
    email: new FormControl('', [Validators.required,Validators.pattern('^[a-zA-Z0-9_+&*-]+(?:.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+.)+[a-zA-Z]{2,7}$')]),

    permanentCountryId: new FormControl('', Validators.required),
    permanentStatesId: new FormControl('', Validators.required),
    permanentDistrictsId: new FormControl('', Validators.required),
    permanentMunicipalitiesId: new FormControl('', Validators.required),

    temporaryCountryId: new FormControl('', Validators.required),
    temporaryStatesId: new FormControl('', Validators.required),
    temporaryDistrictsId: new FormControl('', Validators.required),
    temporaryMunicipalitiesId: new FormControl('', Validators.required),

    nationality: new FormControl('', [Validators.required]),
    fatherName: new FormControl('', [Validators.required]),
    motherName: new FormControl('', [Validators.required]),
    grandFatherName: new FormControl('', [Validators.required]),
    documentType: new FormControl('', [Validators.required]),
    citizenshipNumber: new FormControl(''),

    citizenshipFrontEncodedImage: new FormControl(''),
    citizenshipBackEncodedImage: new FormControl(''),
    passportEncodedImage: new FormControl(''),
    profileEncodedImage: new FormControl(''),

    passportNumber: new FormControl(''),
    branch: new FormControl('', [Validators.required]),
    branchCode: new FormControl('', [Validators.required]),
  });

  constructor(
    private customerService: CustomerService,
    private router:Router,
    private addressService: AddressService
    ,public dialogRef: MatDialogRef<AddCustomerComponent>
  ) {}
  ngOnInit() {
    // called after the constructor and called  after the first ngOnChanges()
    this.getAddress();
    this.addressService.getCountry()!.subscribe((data) => {
      this.permanentCountries = data;
      this.temporaryCountries = data;
    });
    this.addressService.getState()!.subscribe((data) => {
      this.permanentStates = data;
      this.temporaryStates = data;
    });
  }

  public getAddress() {
    this.addressService.getAddress().subscribe(
      (response: Address[]) => {
        this.permanentAddress = response;
        this.temporaryAddress = response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }
  onChangePermanentAddress(e) {
    this.sameAsPermanntCheckBox =  e.target.checked;

    if( e.target.checked==true){
  
    let countryId: any = this.customerForm.value.permanentCountryId;
    let statesId: any = this.customerForm.value.permanentStatesId;
    this.onChangeTemporaryState(statesId);
    let districtsId: any = this.customerForm.value.permanentDistrictsId;
    this.onChangeTemporaryDistrict(districtsId);
    let municipalitiesId: any =
      this.customerForm.value.permanentMunicipalitiesId;
    this.customerForm.get('temporaryCountryId')?.setValue(countryId);
    this.customerForm.get('temporaryStatesId')?.setValue(statesId);
    this.customerForm.get('temporaryDistrictsId')?.setValue(districtsId);
    this.customerForm
      .get('temporaryMunicipalitiesId')
      ?.setValue(municipalitiesId);
    e.preventDefault();
    }
    else{
  
      let countryId: any = this.customerForm.value.permanentCountryId;
      let statesId: any = this.customerForm.value.permanentStatesId;
      this.onChangeTemporaryState(statesId);
      let districtsId: any = this.customerForm.value.permanentDistrictsId;
      this.onChangeTemporaryDistrict(districtsId);
      let municipalitiesId: any =
        this.customerForm.value.permanentMunicipalitiesId;
      this.customerForm.get('temporaryCountryId')?.setValue(null);
      this.customerForm.get('temporaryStatesId')?.setValue(null);
      this.customerForm.get('temporaryDistrictsId')?.setValue(null);
      this.customerForm
        .get('temporaryMunicipalitiesId')
        ?.setValue(null);
      e.preventDefault();
      }
  }
  onChangePermanentCountry(countryId: any) {
    if (countryId) {
      this.addressService.getState().subscribe((data) => {
        this.permanentStates = data;
        this.permanentDistricts = null;
      });
    } else {
      this.permanentStates = null;
      this.permanentDistricts = null;
    }
  }
  onChangePermanentState(id: any) {
    if (id) {
      this.addressService.getDistrict(id).subscribe((data) => {
        this.permanentDistricts = data;
      });
    } else {
      this.permanentDistricts = null;
    }
  }

  onChangePermanentDistrict(id: any) {
    if (id) {
      this.addressService
        .getMunicipality(id)
        .subscribe((data) => (this.permanentMunicipalities = data));
    }
  }
  onChangeTemporaryCountry(countryId: any) {
    if (countryId) {
      this.addressService.getState().subscribe((data) => {
        this.temporaryStates = data;
        this.temporaryDistricts = null;
      });
    } else {
      this.temporaryStates = null;
      this.temporaryDistricts = null;
    }
  }
  onChangeTemporaryState(id: any) {
    if (id) {
      this.addressService.getDistrict(id).subscribe((data) => {
        this.temporaryDistricts = data;
      });
    } else {
      this.temporaryDistricts = null;
    }
  }

  onChangeTemporaryDistrict(id: any) {
    if (id) {
      this.addressService
        .getMunicipality(id)
        .subscribe((data) => (this.temporaryMunicipalities = data));
    }
  }
  onSubmit() {
    this.submitted = true;

    
    var cfeiImg = this.customerForm.controls['citizenshipFrontEncodedImage'];
    cfeiImg.setValue(this.citizenshipFrontEncodedImg);

    
    var cbeiImg = this.customerForm.controls['citizenshipBackEncodedImage'];
    cbeiImg.setValue(this.citizenshipBackEncodedImg);

 
    var pImg = this.customerForm.controls['passportEncodedImage'];
    pImg.setValue(this.passportEncodedImg);

   
    var prImg = this.customerForm.controls['profileEncodedImage'];
    prImg.setValue(this.profileEncodedImg);

    if (this.customerForm.valid) {
      console.log(this.customerForm.value)
      this.customerService.save(this.customerForm.value).subscribe({
        next: (response) => {
        
          alert('Response from api : ' + response['message']);
          if (response['message'] === 'Customer added successfully') {
            
            this.dialogRef.close([]);
            this.reloadCurrentRoute();
          }
        },
        error: (error) => {

          alert('Error occor : ' + error['message']);
        },
        complete: () => {},
      });
    } else {
      alert('please fill required field');
    }
  }
  //for front select
  selectCitizenshipFrontImg(event) {
    var files = event.target.files;
    this.selectedCitizenshipFrontImg = event.target.files;
    var file = files[0];

    if (files && file) {
      var reader = new FileReader();

      reader.onload = this.handleFrontImg.bind(this);

      reader.readAsBinaryString(file);
    }
  }
  handleFrontImg(event) {
    var binaryString = event.target.result;
    this.citizenshipFrontEncodedImg = window.btoa(binaryString);
  }

  //for back select
  selectCitizenshipBackImg(event) {
    var files = event.target.files;
    this.selectedCitizenshipBackImg = event.target.files;
    var file = files[0];

    if (files && file) {
      var reader = new FileReader();

      reader.onload = this.handleBackImg.bind(this);

      reader.readAsBinaryString(file);
    }
  }
  handleBackImg(event) {
    var binaryString = event.target.result;
    this.citizenshipBackEncodedImg = window.btoa(binaryString);
  }
  //for passport select
  selectPassportImg(event) {
    var files = event.target.files;
    this.selectedPassportImg = event.target.files;
    var file = files[0];

    if (files && file) {
      var reader = new FileReader();

      reader.onload = this.handlePassportImg.bind(this);

      reader.readAsBinaryString(file);
    }
  }
  handlePassportImg(event) {
    var binaryString = event.target.result;
    this.passportEncodedImg = window.btoa(binaryString);
  }
  //for profile select
  selectProfileImg(event) {
    var files = event.target.files;
    this.selectedProfileImg = event.target.files;
    var file = files[0];

    if (files && file) {
      var reader = new FileReader();

      reader.onload = this.handleProfileImg.bind(this);

      reader.readAsBinaryString(file);
    }
  }
  handleProfileImg(event) {
    var binaryString = event.target.result;
    this.profileEncodedImg = window.btoa(binaryString);
  }


  onDocumentChange(selectedDocument: string) {
   

    switch (selectedDocument) {
      case 'citizenship': {
    
        this.customerForm.controls['citizenshipNumber'].setValidators([Validators.required]);
        this.customerForm.controls['passportNumber'].reset();  
        this.customerForm.controls['passportNumber'].setValidators(null);  
        this.passportEncodedImg=null;
                this.customerForm.controls['passportNumber'].updateValueAndValidity(); 
        this.selectedPassportImg=false; 
        this.showCitizenshipFields=true;
        this.showPassportFields=false;
  
  
        break;
      }
      case 'passport': {
        this.customerForm.controls['passportNumber'].setValidators([Validators.required]);
              this.customerForm.controls['citizenshipNumber'].reset();
              this.customerForm.controls['citizenshipNumber'].setValidators(null);
              this.citizenshipFrontEncodedImg=null;
              this.citizenshipBackEncodedImg=null;
              this.customerForm.controls['citizenshipNumber'].updateValueAndValidity();  
              this.selectedCitizenshipFrontImg=false; 
              this.selectedCitizenshipBackImg=false; 

        this.showPassportFields=true;
        this.showCitizenshipFields=false;

        break;
      }
     
    }
  }
  
  onBranchChange(selectedBranch: string) {
    let branchCode = '';
    switch (selectedBranch) {
      case 'branch1': {
        branchCode = '11001290';
        break;
      }
      case 'branch2': {
        branchCode = '11001293';
        break;
      }
      case 'branch3': {
        branchCode = '11001300';
        break;
      }
    }
    this.customerForm.get('branchCode')?.setValue(branchCode);
  }
  reloadCurrentRoute() {
    let currentUrl = this.router.url;
    this.router.navigateByUrl('/', {skipLocationChange: true}).then(()  =>{
        this.router.navigate([currentUrl]);
    });
  }
}
