import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { Customer } from './customer';

@Injectable()
export class CustomerService {
  
  private overloadUrl = 'app/heroes';  // URL to web api

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  constructor(private http: Http) { }

  save(customer : Customer): Promise<Customer> { 

    // Send selfRegistration to server

    // console.log(SelfRegistration.value);
     
    return this.http.get(this.overloadUrl)
               .toPromise()
               .then(response => response.json().data as Customer)
               .catch(this.handleError);
  }


}

