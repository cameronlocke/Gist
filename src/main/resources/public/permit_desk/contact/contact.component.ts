import { Component, Input, Output, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';

@Component({
  moduleId    : module.id,
  selector    : 'contact-component',
  templateUrl : 'contact.component.html',
})

export class ContactComponent {

   @Input() contactGroup : FormGroup;

   constructor(private validationService: ValidationService) { }

}
