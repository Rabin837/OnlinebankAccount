
export interface Customer {
customerId:string;
name:string;
dob:Date;
gender:string;
phone:string;
email:string;

permanentAddress: Address;
temporaryAddress: Address;

nationality:string;
fatherName:string;
motherName:string;
grandFatherName:string;
documentType: string;
citizenshipNumber:string;
passportNumber:string;
cifId:string;
branch:string;
branchCode:string;
customerAddedDate:string;
customerUpdatedDate:string;

 citizenshipFrontImagePath:string;
 citizenshipBackImagePath:string;
 passportImagePath:string;
 profileImagePath:string;
   
 citizenshipFrontEncodedImage: string;
 citizenshipBackEncodedImage: string;
 passportEncodedImage: string;
 profileEncodedImage: string;
}
export interface Address {
    country:Country;
    districts:Districts;
    states:States;
    municipalities:Municipalities;
}
export interface Country {
    name: string;
}
export interface Districts {
    name: string;

}export interface States {
    name: string;

}export interface Municipalities {
    name: string;
type:string;
}