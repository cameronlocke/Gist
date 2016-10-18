import { Component, Input, Output, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Collection } from '../collection/collection';
import { CollectionService } from '../collection/collection.service';
import { ValidationService } from '../validation/validation.service';

@Component({
  moduleId    : module.id,
  selector    : 'address-component',
  templateUrl : 'address.component.html',
  providers   : [CollectionService, ValidationService ]
})

export class AddressComponent implements OnInit {

   @Input() addressGroup : FormGroup;

    // Dropdowns
    state : Collection;
    states : Collection[];

    constructor(private validationService: ValidationService,
                private collectionService: CollectionService
    ) { }

    ngOnInit():void {

         // Loadup  states (for drop down)
         this.collectionService.findByCollection('State').then(states => this.states = states)
    }

//TODO Cam over to you to impleemtn autocomplete (I have no idea)

// Populate "real" addresss fields with value on "top of list" as you type (so the user can see expected result)

//   onSearchKeyDown () {
//         if ( (addressSearchService(addressSearch).found ) {
//            this.addressLine1 = addressSearch.addressLine1;
//            this.addressLine2 = addressSearch.addressLine1;
//            this.suburb = addressSearch.suburb;
//            this.postcode = addressSearch.postcode;
//         }
//   }



}
