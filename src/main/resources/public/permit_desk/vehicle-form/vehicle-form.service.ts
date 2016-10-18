import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { VehicleForm } from './vehicle-form';

@Injectable()
export class VehicleFormService {

  private url = 'app/vehicleForm';

  constructor(private http: Http) { }

  findOne(vehicleId : number): Promise<VehicleForm> {

    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as VehicleForm)
               .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {

     console.error('An error occurred', error); // TODO need to productionify this, for demo purposes only
     return Promise.reject(error.message || error);

  }

}

