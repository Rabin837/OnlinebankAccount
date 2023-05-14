import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Address, Countries, Districts, Municipality, States } from '../class/address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  private baseUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) {}
  public getAddress(): Observable<Address[]> {
    return this.http.get<Address[]>(`${this.baseUrl}address/showAll`);
  } 
  public getCountry(): Observable<Countries[]> {
    return this.http.get<Countries[]>(`${this.baseUrl}country/showAll`);
  }
  public getState(): Observable<States[]> {
    return this.http.get<States[]>(`${this.baseUrl}state/showAll`);
  }
  public getDistrict(id: any): Observable<Districts[]> {
    return this.http.get<Districts[]>(
      `${this.baseUrl}district/districtFromState/${id}`
    );
  }
  public getMunicipality(id: any): Observable<Municipality[]> {
    return this.http.get<Municipality[]>(
      `${this.baseUrl}district/municipalityFromDistrict/${id}`
    );
  }
}
