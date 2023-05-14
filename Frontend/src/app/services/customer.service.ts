import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/internal/Observable';
import { Customer } from '../class/customer';
import { PaginationData } from '../class/pagination-data';
import { CustomerCustomerAccount } from '../class/customer-customer-account';


@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = "http://localhost:8080/customer/";

  constructor(private http: HttpClient) { }
  save(customerData: any){
    return this.http.post(`${this.baseUrl}add`, customerData);
  }
  getAllCustomer(pageNumber: number,pageSize:number):Observable<PaginationData[]>{
    return this.http.get<PaginationData[]>(`${this.baseUrl}findAll/${pageNumber}/${pageSize}`);


  }
  getCustomer(customerId: any): Observable<Customer[]>{
    return this.http.get<Customer[]>(`${this.baseUrl}find/${customerId}`);
  }
  /*
  deleteOneCustomer(customerId: any){
    return this.http.delete(`${this.baseUrl}delete/${customerId}`);

  }
  */
  updateCustomer(customerId: any, customerUpdateData: any){
    return this.http.put(`${this.baseUrl}update/${customerId}`,customerUpdateData);
  }
  getByCifID(params: any): Observable<CustomerCustomerAccount[]>{
    return this.http.get<CustomerCustomerAccount[]>(`${this.baseUrl}getByCifId`,{params});
  }
}
