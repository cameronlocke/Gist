import { Component, Input } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
    moduleId: module.id,
    selector: 'axle-component',
    templateUrl: 'axle.component.html'
})
export class AxleComponent {

    @Input() axlegroup: FormGroup;

}
