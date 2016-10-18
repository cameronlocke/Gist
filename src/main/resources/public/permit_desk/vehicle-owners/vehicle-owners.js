"use strict";
var vehicle_owner_1 = require('./vehicle-owner');
var VehicleOwners = (function () {
    function VehicleOwners() {
        this.vehicleOwners = [];
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(1, 'Batman'));
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(2, 'Spider-Man'));
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(3, 'Superman'));
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(4, 'Flash'));
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(5, 'Punisher'));
        this.vehicleOwners.push(new vehicle_owner_1.VehicleOwner(6, 'Mario'));
    }
    return VehicleOwners;
}());
exports.VehicleOwners = VehicleOwners;
//# sourceMappingURL=vehicle-owners.js.map