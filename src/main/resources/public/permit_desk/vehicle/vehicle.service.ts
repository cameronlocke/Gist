import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Vehicle } from './vehicle';


@Injectable()
export class VehicleService {

  private baseUrl = 'vehicle';  

  private headers = new Headers({'Content-Type': 'application/json'});

  constructor(private http: Http) { }

  findOne(vehicleId : number): Promise<Vehicle> {
    const url = `${this.baseUrl}/${vehicleId}`;
    return this.http
      .get(url)
      .toPromise()
      .then(response => response.json().data as Vehicle)
      .catch(this.handleError);
  }

  findAll(): Promise<Vehicle[]> { 
    return this.http
               .get(this.baseUrl)
               .toPromise()
               .then(response => response.json().data as Vehicle[])
               .catch(this.handleError);
  }

  update(vehicle: Vehicle): Promise<Vehicle> {
    const url = `${this.baseUrl}/${vehicle.vehicleId}`;
    return this.http
      .put(url, JSON.stringify(vehicle), {headers: this.headers})
      .toPromise()
      .then(() => vehicle)
      .catch(this.handleError);
  }



  create(vehicle: Vehicle): Promise<Vehicle> {
    return this.http
       .post(this.baseUrl, JSON.stringify(vehicle), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data)
      .catch(this.handleError);
  }


  delete(id: number): Promise<void> {
  const url = `${this.baseUrl}/${id}`;
  return this.http.delete(url, {headers: this.headers})
    .toPromise()
    .then(() => null)
    .catch(this.handleError);
}


  private handleError(error: any): Promise<any> {

     console.error('An error occurred', error); // TODO need to productionify this, for demo purposes only
     return Promise.reject(error.message || error);

  }

}


