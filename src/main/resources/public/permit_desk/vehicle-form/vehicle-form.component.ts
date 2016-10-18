import { Component, Input, OnInit } from '@angular/core';
import { Location } from '@angular/common';
import { FormBuilder, FormGroup, FormArray, Validators } from '@angular/forms';
import { ActivatedRoute, Params }   from '@angular/router';
import { DimensionComponent } from '../dimension/dimension.component';
import { AxleComponent } from '../axle/axle.component';
import { ValidationService } from '../validation/validation.service';
import { CollectionService } from '../collection/collection.service';
import { VehicleFormService } from '../vehicle-form/vehicle-form.service';

import {DataTableModule,SharedModule, TabViewModule, MessagesModule } from 'primeng/primeng';

import { Collection } from '../collection/collection';
import { Axle } from '../axle/axle';


@Component({
  moduleId: module.id,
  selector: 'vehicle-form',
  templateUrl: 'vehicle-form.component.html',
  providers : [ValidationService, CollectionService, VehicleFormService ]
})

export class VehicleFormComponent implements OnInit {

//   @Input('formMode') 
//   public formMode : number;  // FormMode;  // (must create, edit or show) - may be based on security or business processes.

//   @Input('vehicleId')
//   public vehicleId : number; // Used in edit and show modes.

// Global form flags.

    public axle : Axle;

    public active : boolean;

    // Inject FormBuilder and Validation Services
    constructor(private _fb: FormBuilder, 
                private validationService: ValidationService,
                private collectionService: CollectionService,
		private route: ActivatedRoute,
                private location : Location,
                private vehicleFormService: VehicleFormService
    ) { }


    // TODO Fix collection lookup
    // This is not very good way to do this, findCollection should return collecton (not a promise) then code like
    // vehicleTypes = this.CollectionService.findByCollection('VehicleType');
    // Also dont think we need the single collection objects (as selected item key should map directly to model).

    // VehicleType dropdown
    vehicleType : Collection;
    vehicleTypes : Collection[];
    getVehicleTypes():void {
       this.collectionService.findByCollection('VehicleType').then(vehicleTypes => this.vehicleTypes = vehicleTypes);
    }

    // State dropdown
    state : Collection;
    states : Collection[];
    getStates():void {
       this.collectionService.findByCollection('State').then(states => this.states = states);
    }

    // Manufacturer dropdown
    manufacturer : Collection;
    manufacturers : Collection[];
    getManufacturers():void {
       this.collectionService.findByCollection('Manufacturer').then(manufacturers => this.manufacturers = manufacturers);
    }

    // Source of Information dropdown
    source : Collection;
    sources : Collection[];
    getSources():void {
       this.collectionService.findByCollection('SourceOfInformation').then(sources => this.sources = sources);
    }

    // Top-level control (i.e. the form)
    vehicleForm : FormGroup;  

    //TODO This should be "mapped" to model.
    attachments: any[] = [];

    // Initialise  
    ngOnInit():void {
 
	// Build the form (using model-driven approach)
	this.vehicleForm = this._fb.group({
        applicationId           : [{value:"", disabled:true}, ],
		vehicleType	: ["1",],
		plate		: ["882FBN",],
		plateState 	: ["4",],
	    	vin		: ["123dds23424c",],
		chassisNo	: ["243vcdsc",],
		engineNo	: ["324535",],
       		manufacturer    : ["2",], 
	    model               : ["BT50",],
        noOfAxles   		: [0,],
        tare                    : ["9.25",],
        gvm						: ["26.5",],
        gcm						: ["80.5",],
        sourceOfInformation		: ["2",],
		dimension               : this._fb.group({
			width		: ["3.5",],
			height		: ["3.3",],
			length		: ["14.5",],
			frontOverhang	: ["1.2",],
			rearOverhang	: ["1.8",],
			frontProjection	: ["2.1",],
			rearProjection	: ["10.5",],
			lhsProjection	: ["1.0",],
			rhsProjection	: ["0.0",]
	        }),
                axleConfiguration       : this._fb.group({
                        noOfAxles	: [,],
                        grossMass	: [,]
		}),
		axles 			: this._fb.array([this.initAxle(1),]),
		notes			: ["",],
// Somehow need to bind attachments string array to form control (so can be sent to B/E on submit
//		attachments     : this._fb.array(["",]),
		permitNo		: ["",]   // ATO Number, only available if has been issued
	});

	// Form created so load if application_id passed.
	//	this.route.params.forEach((params: Params) => {
	//		let id = +params['id'];
	//      	if ((id != null) && (id != 0 )) {
	//			this.vehicleFormService.findOne(id).then(vehicleForm => this.vehicleForm.value = vehicleForm);
	//		}


	// If formMode is Edit or Show then populate the page...
	// The form is defined initally as READ-ONLY/DISABLED, however if edit or create then make editable. 

	// Populate the drop-downs
	this.getVehicleTypes();
        this.getStates();
	this.getManufacturers();
	this.getSources();  // This may be a bunch of check-boxes as can have multiple.



//TODO Do I pass the form mode as a parameter in the URL,
// or I create a "mode" service (take application and user info to determine read-only or not )
//
//		let mode = params['mode'];
//		if ((mode = "create" ) || (mode = "edit" )) {
//	                this.vehicleForm.prop.readonly('readonly',false);
//	        } else {
//	                this.vehicleForm.prop.readonly('readonly',true);
//		}
//	});


	this.active = true;

        } // OnInit
   

	initAxle(axleNo : number) {        // create and initialize a new axle 
	    return this._fb.group({
  			axleNo: [axleNo, ],    
			mass : [, Validators.required],
			noOfTyres : [, Validators.required],
			tyreWidth : [, Validators.required],
			groundContactLength : [, Validators.required],
			groundContactWidth : [, Validators.required]
        	});
	}

        // This will be call the number of axles in the combination (summed form vehicleCombination).
	addAxle(axleNo : number) {
        // add axle to the list
	    const control = <FormArray>this.vehicleForm.controls['axles'];
	    control.push(this.initAxle(axleNo));
	}

	removeAxle(i : number) {
	// remove axle from the list, not sure if this is used or we remove all.
            const control = <FormArray>this.vehicleForm.controls['axles'];
            control.removeAt(i);
        }

	clearAxles() {
        // pop-off till no more
	    const control = <FormArray>this.vehicleForm.controls['axles'];
	    for (var i = control.length; i > 0; i--) {
 		 control.removeAt(i);
	    }
	}



	// Events
	onReadOnly(value : boolean): void {
	        //TODO Make all form read-only/disabled or enabled.
                // Can't just remove read-only attributes as requires DOM property manipulation.
	}


	onSubmit(value : string): void {
		//TODO Show a spinny image thing, disable page
	        // this.vehcileFormService.save(this.vehicleForm.value).then(vehicleForm => this.vehicleForm = vehicleForm);
		//TODO Hide a spinny image thing, enable page.
                // Process/display errors.  If All OK return to home or go to payment page for delivery (if fees outstanding)
	       console.log('Submitted value: ', value);                  
	}

 	onReset() : void {
	       //TODO Reset form to initialisation state.
               this.vehicleForm.reset();
	}

	goBack(): void {
	  console.log('Location: ', this.location.path);
	  this.location.back();
	}

   	onChangeAxles() : void {
	        var i;
	        var j = this.vehicleForm.controls['noOfAxles'].value;
		console.log('No Of Axles: ', this.vehicleForm.controls['noOfAxles'].value);
        	this.clearAxles();
	        for (i = 0; i < j; i++) { 
        	       this.addAxle(i+1);
		}
	}
}


