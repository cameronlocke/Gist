import { VehicleOwner } from './vehicle-owner';

export class VehicleOwners {

    vehicleOwners: VehicleOwner[];

    constructor() {
        this.vehicleOwners = [];
        this.vehicleOwners.push(new VehicleOwner(1, 'Batman'));
        this.vehicleOwners.push(new VehicleOwner(2, 'Spider-Man'));
        this.vehicleOwners.push(new VehicleOwner(3, 'Superman'));
        this.vehicleOwners.push(new VehicleOwner(4, 'Flash'));
        this.vehicleOwners.push(new VehicleOwner(5, 'Punisher'));
        this.vehicleOwners.push(new VehicleOwner(6, 'Mario'));
    }
}

