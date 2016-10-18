import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';
import { CollectionService } from '../collection/collection.service';
import { SearchFormService } from '../search-form/search-form.service';

import {OverlayPanelModule} from 'primeng/primeng';
import {Message, MessagesModule, GrowlModule } from 'primeng/primeng';

// Objects
import { Collection } from '../collection/collection';
import { SearchResult, SearchCriteria } from '../search-form/search-form';

@Component({
  moduleId: module.id,
  selector: 'search-form',
  templateUrl: 'search-form.component.html',
  providers : [ValidationService, CollectionService, SearchFormService ]
})

export class SearchFormComponent implements OnInit {

// Global form flags.

    public active : boolean;

    msgs: Message[] = [];

    // Inject FormBuilder and Validation Services
    constructor(private _fb: FormBuilder, 
                private validationService: ValidationService,
                private searchFormService: SearchFormService,
                private collectionService: CollectionService) { }

    selectedResult: SearchResult;
    searchResults: SearchResult[];
    getSearchResults() : void {
      this.searchFormService.findSearchResults(this.searchForm.value).then(searchResults => this.searchResults = searchResults);
    }

    // Statuses
    status : Collection;
    statuses : Collection[];
    getStatuses():void {
       this.collectionService.findByCollection('Status').then(statuses => this.statuses = statuses);
//       this.getCollection('Status');
//       this.statuses = this.collections;
    }

    // States
    state : Collection;
    states : Collection[];
    getStates():void {
       this.collectionService.findByCollection('State').then(states => this.states = states);
//       this.getCollection('State');
//       this.states = this.collections;
    }

    // ApplicationTypes
    applicationType : Collection;
    applicationTypes : Collection[];
    getApplicationTypes():void {
       this.collectionService.findByCollection('ApplicationType').then(applicationTypes => this.applicationTypes = applicationTypes);
//       this.getCollection('ApplicationType');
//       this.applicationTypes = this.collections;
    }

    vehicleType : Collection;
    vehicleTypes : Collection[];
    getVehicleTypes():void {
       this.collectionService.findByCollection('VehicleType').then(vehicleTypes => this.vehicleTypes = vehicleTypes);
//       this.getCollection('VehicleType');
//       this.vehicleTypes = this.collections;    
    }

    // Generic get Collections

//    collection : Collection;
//    collections : Collection[];
//    getCollection(collectionName : string) : void {
//       this.collectionService.findByCollection(collectionName).then(collections => this.collections = collections);
//       this.collectionService.findByCollection('States').then(states => this.states = states);
//    }

    // Top-level control (i.e. the search form)
    public searchForm : FormGroup;  

    // Initialise  
    ngOnInit(): void {
 
      // Build the form (using model-driven approach)
	this.searchForm = this._fb.group({
        	statusId 		: [0,],
		applicationType		: [0,],
		permitNo		: ["",],
		submittedFrom		: ["",],
		submittedTo		: ["",],
		lastActionFrom		: ["",],
	   	lastActionTo		: ["",],
		decisionFrom		: ["",],  // Issue, Reject, Return
		decisionTo		: ["",],
		authorisedFrom		: ["",],
		authorisedTo		: ["",],
      // Vehicle
		vehicleType		: ["",],
		plateNo			: ["",],
		plateState 		: ["",], 
	        vin			: ["",],
		chassisNo		: ["",],
		engineNo		: ["",],
      // Geometry (route/area)
		designatedRoute		: ["",],
		areaOfOperation		: ["",],
		mapGeometry		: ["",]
	});

      // Get first result set using initial criteria...
      this.getSearchResults();      

      // Populate the drop-downs
      this.getStatuses();
      this.getStates();
      this.getApplicationTypes();
      this.getVehicleTypes();

      this.active = true;

    } // OnInit
   
    // Events

//    onSearch(): void {
	//TODO Show a spinny imgae thing, disable search page (perhaps a dialog?)
//        console.log('Submitted value: ',  this.searchForm.value);  
//        this.searchFormService.find SearchResults(this.searchForm.value).then(searchResults => this.searchResults = searchResults);
	//TODO Hide a spinny image thing, enable search page.
//    }

    onSubmit(value: string): void {  
       console.log('Submitted value: ', this.searchForm.value);  
    }

    onRowSelect(event : SearchResult): void {
       console.log('Selected Row: ', event.applicationId);
       this.selectedResult = event;
        this.msgs = [];
        this.msgs.push({severity: 'info', summary: 'Application ID#', detail: event.description });
    }

//TODO display overlay panel
//       overlaypanel.toggle(event);


// TODO Ensure the following code adds keyboard navigation to PrimeNG datatable.
// PrimeNG datagrid doesn't allow keybaord navigation.  Supposedly this code does so I need to figure out how to implement.
// @HostListener('keydown.arrowUp', ['$event']) for iterating [(selection)] 
//

    onReset() : void {
       this.searchForm.reset();
//       this.searchForm.controls.statusId.setValue(0);
//       this.searchForm.controls.applicationType.setValue(0);
    }

}
