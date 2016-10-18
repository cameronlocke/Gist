"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
var core_1 = require('@angular/core');
var vehicle_service_1 = require('../vehicle/vehicle.service');
var VehicleBuilderComponent = (function () {
    function VehicleBuilderComponent(vehicleService) {
        this.vehicleService = vehicleService;
    }
    VehicleBuilderComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.selectedVehicles = [];
        this.vehicleService.findAll().then(function (vehicles) { return _this.availableVehicles = vehicles; });
    };
    VehicleBuilderComponent.prototype.dragStart = function (event, vehicle) {
        this.draggedVehicle = vehicle;
    };
    VehicleBuilderComponent.prototype.drop = function (event) {
        if (this.draggedVehicle) {
            this.selectedVehicles.push(this.draggedVehicle);
            this.availableVehicles.splice(this.findIndex(this.draggedVehicle), 1);
            this.draggedVehicle = null;
        }
    };
    VehicleBuilderComponent.prototype.dragEnd = function (event) {
        this.draggedVehicle = null;
    };
    VehicleBuilderComponent.prototype.findIndex = function (vehicle) {
        // Is there someway to do this without the iteration.
        var index = -1;
        for (var i = 0; i < this.availableVehicles.length; i++) {
            if (vehicle.vehicleId === this.availableVehicles[i].vehicleId) {
                index = i;
                break;
            }
        }
        return index;
    };
    VehicleBuilderComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vehicle-builder-component',
            templateUrl: 'vehicle-builder.component.html',
        }), 
        __metadata('design:paramtypes', [vehicle_service_1.VehicleService])
    ], VehicleBuilderComponent);
    return VehicleBuilderComponent;
}());
exports.VehicleBuilderComponent = VehicleBuilderComponent;
//# sourceMappingURL=vehicle-builder.component.js.map