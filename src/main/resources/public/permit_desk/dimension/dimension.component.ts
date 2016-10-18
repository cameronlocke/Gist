import { Component, Input, Output, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';

@Component({
  moduleId    : module.id,
  selector    : 'dimension-component',
  templateUrl : 'dimension.component.html',
  providers   : [ValidationService ]
})

export class DimensionComponent {

   @Input() dimensionGroup : FormGroup;

    constructor(private validationService: ValidationService) { }

}
