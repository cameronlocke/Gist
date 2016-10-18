import { Component, Input, Output, OnInit  } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ValidationService } from '../validation/validation.service';

@Component({
  moduleId    : module.id,
  selector    : 'user-component',
  templateUrl : 'user.component.html'
})

export class UserComponent {

	@Input() userGroup : FormGroup;

}
