import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';


@Component({
  moduleId: module.id,
  selector: 'search-actions',
  templateUrl: 'search-actions.component.html'
})

export class SearchActionsComponent {

 @Input()
 public formGroup : FormGroup;

}
