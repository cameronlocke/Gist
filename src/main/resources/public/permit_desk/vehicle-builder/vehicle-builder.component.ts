import { Component, Input, OnInit  } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Collection } from '../collection/collection';
import { CollectionService } from '../collection/collection.service';
import { Vehicle } from '../vehicle/vehicle'; 
import { VehicleService } from '../vehicle/vehicle.service';

import {DragDropModule} from 'primeng/primeng';

@Component({
  moduleId    : module.id,
  selector    : 'vehicle-builder-component',
  templateUrl : 'vehicle-builder.component.html',
//  providers   : [VehicleCombinationService ]
})

export class VehicleBuilderComponent implements OnInit {

    availableVehicles: Vehicle[];
    
    selectedVehicles: Vehicle[];
    
    draggedVehicle: Vehicle;
    
    constructor(private vehicleService: VehicleService) { }
    
    ngOnInit() {
        this.selectedVehicles = [];
        this.vehicleService.findAll().then(vehicles => this.availableVehicles = vehicles);
    }
    
    dragStart(event,vehicle: Vehicle) {
        this.draggedVehicle = vehicle;
    }
    
    drop(event) {
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

