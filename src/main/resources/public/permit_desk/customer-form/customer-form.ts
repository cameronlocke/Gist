import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';

import { Message } from 'primeng/primeng';

// Components
import { AddressComponent } from '../address/address.component';
import { ContactComponent } from '../contact/contact.component';
import { UserComponent } from '../user/user.component';

// ALL ADMIN pages should be some sort of find / search / list
// And then overlay dialog to edit (with OK and cancel).


@Component({
  moduleId: module.id,
  selector: 'customer-form',
  templateUrl: 'customer-form.html',
  providers : [ValidationService ]
})

export class SelfRegistrationFormComponent  implements OnInit {

// Global form flags.

    public active : boolean; // not sure about this 

    // Inject FormBuilder and Validation Services
    constructor(private _fb: FormBuilder, 
                private validationService: ValidationService)
    { }


    // Errors, Waring, Information.
    msgs: Message[];

    // Top-level control (i.e. the search form)
    searchForm : FormGroup;  

    // Initialise  
    ngOnInit():void {
 
        // Build the form (using model-driven approach)
	this.searchForm = this._fb.group({
		customerName		: ["",],
		customerPermitNo	: ["",],  //AccreditationNo / AHHO
		customerABN		: ["",],  
		customerACN		: ["",],  
	});


    } // OnInit
   
    // Events
    onSubmit(value: string): void {  
       console.log('Submitted value: ', value);  
    }

}
