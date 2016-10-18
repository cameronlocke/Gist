import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';


@Component({
  moduleId: module.id,
  selector: 'page-actions',
  templateUrl: 'page-actions.component.html'
})

export class PageActionsComponent {

 @Input()
 public formGroup : FormGroup;

}
