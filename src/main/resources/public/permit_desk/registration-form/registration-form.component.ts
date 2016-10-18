import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';
import { RegistrationFormService } from '../registration-form/registration-form.service';

import { SelectItem, Message } from 'primeng/primeng';

// Components
import { AddressComponent } from '../address/address.component';
import { ContactComponent } from '../contact/contact.component';
import { UserComponent } from '../user/user.component';
import { EoIComponent } from '../eoi/eoi.component';

@Component({
  moduleId: module.id,
  selector: 'registration-form',
  templateUrl: 'registration-form.component.html',
  providers : [ ValidationService ]
})

export class RegistrationFormComponent implements OnInit {

// Global form flags.

    public submitted : boolean;  // Used to hide entry page if already submitted

    public active : boolean; // not sure about this 

    // Inject FormBuilder and Validation Services
    constructor(private _fb: FormBuilder, 
                private validationService: ValidationService, 
                private registrationFormService : RegistrationFormService) { }

    msgs: Message[];
    uploadedFiles: any[] = [];

    // Top-level control (i.e. the form)
    selfRegistrationForm : FormGroup;  


    // Initialise  
    ngOnInit():void {
 
      this.submitted = false;

      this.active = true;

      // Build the form (using model-driven approach)
      this.selfRegistrationForm = this._fb.group({
	businessIdentity			: this._fb.group({
		businessName			: ["theGISt", Validators.required],
		tradingName			: ["Overload", Validators.required],
		abn				: ["123-456-78", Validators.compose([Validators.required, ValidationService.abn])],
		contact				: this._fb.group({
			firstName		: ["B-Firstname", Validators.required],
			middleName		: ["B-Middle",],
			lastName		: ["B-Surname", Validators.required],
			phone			: ["0755613342", Validators.required],
			email			: ["business@testing.com", Validators.compose([Validators.required, ValidationService.eMail])]
		}),
		afterHoursPhone			: ["0799999999", Validators.compose([Validators.required, ValidationService.phone])]
	}),
	businessAddress				: this._fb.group({
		addressSearch			: ["", ],
		addressLine1			: ["B-Line1", Validators.required],
		addressLine2			: ["B-line2", ],
         	addressSuburb   		: ["B-Suburb", Validators.required],
		addressState    		: [3, Validators.required],
		addressPostcode 		: ["3214", Validators.compose([Validators.required, ValidationService.postcode])]
        }),
	sameAsBusinessAddress			: [true,],
	postalAddress				: this._fb.group({
		addressSearch			: ["", ],
		addressLine1			: ["P-Line1", Validators.required],
		addressLine2			: ["P-line2", ],
         	addressSuburb   		: ["P-Suburb", Validators.required],
		addressState    		: [4, Validators.required],
		addressPostcode 		: ["4009", Validators.compose([Validators.required, ValidationService.postcode])]
        }),
	delegate				: this._fb.group({
		contact				: this._fb.group({
			firstName		: ["D-Firstname", Validators.required],
			middleName		: ["",],
			lastName		: ["D-Surname", Validators.required],
			phone			: ["0755613342", Validators.required],
			email			: ["delgate@testing.com", Validators.compose([Validators.required, ValidationService.eMail])]
		}),
		eoi				:  this._fb.group({
			dateOfBirth		: ["1966-10-24",  Validators.required],
  			licenceNumber		: ["123-24324-11", Validators.required]
		})
	}),
	sameAsProprietor			: [false, ],
	administrator				: this._fb.group({
		user				: this._fb.group({
			userName			: ["nickw", Validators.required],
			contact				: this._fb.group({
				firstName		: ["A-Firstname", Validators.required],
				middleName		: ["",],
				lastName		: ["A-Surname", Validators.required],
				phone			: ["0755613342", Validators.required],
				email			: ["admin@testing.com", Validators.compose([Validators.required, ValidationService.eMail])]
			}),
			eoi				: this._fb.group({
				dateOfBirth		: ["1966-10-24",  Validators.required],
				licenceNumber		: ["123-24324-11", Validators.required]
			})
		})
	}), 
	notes			: ["", ],
	termsAndConditions	: ["", ValidationService.tickRequired]
      });

    } // OnInit
   
    // Events
    onSubmit(value: string): void {  
       this.submitted = true;
       console.log('Submitted value: ', value); 
       console.log(JSON.stringify(this.selfRegistrationForm.value));
    }

    // Attachments are not uploaded through INPUT control.
    onUpload(event):void  {
        for(let file of event.files) {
            this.uploadedFiles.push(file);
        }
    
        this.msgs = [];
        this.msgs.push({severity: 'info', summary: 'File Uploaded', detail: ''});
    }

}
