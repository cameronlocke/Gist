import {Injectable} from '@angular/core';
import { Headers, Http, Response} from '@angular/http';
import 'rxjs/add/operator/toPromise';

import {SearchCriteria, SearchResult} from './search-form';

@Injectable()
export class SearchFormService {

   private url = 'app/search';
    
   constructor(private http: Http) {}

   findSearchResults(searchCriteria : SearchCriteria): Promise<SearchResult[]> {

      console.log(JSON.stringify(SearchCriteria));

      return this.http.get(this.url)
               .toPromise()
               .then(response => response.json().data as SearchResult[])
               .catch(this.handleError);
   }


   private handleError(error: any): Promise<any> {

     console.error('An error occurred', error); // TODO need to productionify this, for demo purposes only
     return Promise.reject(error.message || error);

  }

}

