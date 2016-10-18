import { Injectable } from '@angular/core';
import { Headers, Http } from '@angular/http';

import 'rxjs/add/operator/toPromise';

import { Collection } from './collection';

@Injectable()
export class CollectionService {

// http:service findCollectionByCollection(collection);
// At moment using in-memory HTTP mock service (refer in-memory-data-service.ts).

  private url : string;

  constructor(private http: Http) { }

  findByCollection(collectionName : string): Promise<Collection[]> {

    this.url = 'collection';

    if ( collectionName == 'State' ) {
        this.url = 'app/state' ;
    }

    if ( collectionName == 'Status' ) {
        this.url = 'app/status' ;
    }

    if ( collectionName == 'ApplicationType' ) {
        this.url = 'app/applicationtype' ;
    }
    if ( collectionName == 'VehicleType' ) {
        this.url = 'app/vehicletype' ;
    };

    if ( collectionName == 'Manufacturer' ) {
        this.url = 'app/manufacturer' ;
    };

    if ( collectionName == 'SourceOfInformation' ) {
        this.url = 'app/sourceOfInformation' ;
    };

    return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as Collection[])
               .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {

     console.error('An error occurred', error); // TODO need to productionify this, for demo purposes only
     return Promise.reject(error.message || error);

  }

}

