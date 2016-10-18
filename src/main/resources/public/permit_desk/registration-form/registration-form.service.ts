import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';
import 'rxjs/add/operator/toPromise';

import { RegistrationForm } from '../registration-form/registration-form';

@Injectable()
export class RegistrationFormService {
  
  private overloadUrl = 'app/heroes';  // URL to web api

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  constructor(private http: Http) { }

  save(registrationForm : RegistrationForm): Promise<RegistrationForm> { 

    // Send RegistrationForm to server

    console.log(JSON.stringify(RegistrationForm));
     
    return this.http.get(this.overloadUrl)
               .toPromise()
               .then(response => response.json().data as RegistrationForm)
               .catch(this.handleError);
  }


}

