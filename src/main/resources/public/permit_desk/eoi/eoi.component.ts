import { Component, Input, Output, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';

@Component({
  moduleId    : module.id,
  selector    : 'eoi-component',
  templateUrl : 'eoi.component.html',
})

export class EoIComponent implements OnInit {

   @Input() eoiGroup : FormGroup;

    constructor(private _fb: FormBuilder, 
                private validationService: ValidationService
    ) { }

    ngOnInit():void {

	this.eoiGroup	= this._fb.group({
		dateOfBirth	: ["1966-10-24",  Validators.required],
		licenceNumber	: ["123-24324-11", Validators.required]
         })
    }


}
