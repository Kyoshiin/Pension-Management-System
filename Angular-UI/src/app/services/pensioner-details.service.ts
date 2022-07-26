import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PensionerDetail } from '../models/pensioner-detail';

@Injectable({
  providedIn: 'root'
})
export class PensionerDetailsService {

  // add your base URL here
  baseUrl: string = 'http://localhost:8200/pensioner';

  constructor(private http: HttpClient) { }

  // Method to process the pension
  getPensionerDetails(adhaarInput: String): Observable<PensionerDetail> {
    console.log("inside getPensine detail \n"+'http://localhost:8200/pensioner/PensionerDetailByAadhaar/' + adhaarInput);

    return this.http.get<PensionerDetail>('http://localhost:8200/pensioner/PensionerDetailByAadhaar/' + adhaarInput);
  }

}
