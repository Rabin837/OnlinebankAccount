import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { CustomerAccount } from '../class/customer-account';

@Injectable({
  providedIn: 'root'
})
export class CustomerAccountService {
  private baseUrl = "http://localhost:8080/customer-account/";

  constructor(private http: HttpClient) { 
    
  }
  getAccounts(customerId: any): Observable<CustomerAccount[]>{

    return this.http.get<CustomerAccount[]>(`${this.baseUrl}get/${customerId}`);
  }
  
  save(customerId: any,customerAccountData: any){
    return this.http.post(`${this.baseUrl}add/${customerId}`, customerAccountData);
  }
  getSpecificAccount(customerId: any,customerAccountId: any): Observable<CustomerAccount[]>{
    return this.http.get<CustomerAccount[]>(`${this.baseUrl}get/${customerId}/${customerAccountId}`);
  }
  updateAccount(customerId: any,customerAccountId:any, customerAccountUpdateData: any){
    return this.http.put(`${this.baseUrl}update/${customerId}/${customerAccountId}`,customerAccountUpdateData);
  }
  getByAccountNumber(params: any): Observable<CustomerAccount[]>{
    return this.http.get<CustomerAccount[]>(`${this.baseUrl}getByAccNum`,{params});
  }
}
