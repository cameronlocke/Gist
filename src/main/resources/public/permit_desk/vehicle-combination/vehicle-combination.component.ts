import { Component, Input, OnInit  } from '@angular/core';
import { FormGroup, FormBuilder } from '@angular/forms';
import { Collection } from '../collection/collection';
import { CollectionService } from '../collection/collection.service';
import { Vehicle } from '../vehicle/vehicle'; 
import { VehicleService } from '../vehicle/vehicle.service';

import {PanelModule, DragDropModule} from 'primeng/primeng';

@Component({
  moduleId    : module.id,
  selector    : 'vehicle-combination-component',
  templateUrl : 'vehicle-combination.component.html',
  providers   : [VehicleService ]
})

export class VehicleCombinationComponent implements OnInit {

    vehicleCombination : FormGroup;

    active : boolean;

    availableVehicles: Vehicle[];
    

    selectedVehicles: Vehicle[];
    
    draggedVehicle: Vehicle;
    
    constructor(private vehicleService: VehicleService, 
                private _fb: FormBuilder) { }
    
    ngOnInit() {
        this.active = true;
        this.selectedVehicles = [];
        this.vehicleService.findAll().then(vehicles => this.availableVehicles = vehicles);

        this.vehicleCombination = this._fb.group ({
          vehicleList : [,],
          dimension : [,],
          axleConfiguration : [,]
        });
    }
    
    dragStart(event,vehicle: Vehicle) {
       console.log('Drag start event:'); 
        this.draggedVehicle = vehicle;
    }
    
    drop(event) {
       console.log('Drop event:'); 
        if(this.draggedVehicle) {
            this.selectedVehicles.push(this.draggedVehicle);
            this.availableVehicles.splice(this.findIndex(this.draggedVehicle), 1);
            this.draggedVehicle = null;
        }
    }
    
    dragEnd(event) {
        this.draggedVehicle = null;
    }
    
    
    findIndex(vehicle: Vehicle) {
    // Is there someway to do this without the iteration.
        let index = -1;
        for(let i = 0; i < this.availableVehicles.length; i++) {
            if(vehicle.vehicleId === this.availableVehicles[i].vehicleId) {
                index = i;
                break;
            }
        }
        return index;
    }

}
