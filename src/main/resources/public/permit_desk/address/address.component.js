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
var forms_1 = require('@angular/forms');
var collection_service_1 = require('../collection/collection.service');
var validation_service_1 = require('../validation/validation.service');
var AddressComponent = (function () {
    function AddressComponent(validationService, collectionService) {
        this.validationService = validationService;
        this.collectionService = collectionService;
    }
    AddressComponent.prototype.ngOnInit = function () {
        var _this = this;
        // Loadup  states (for drop down)
        this.collectionService.findByCollection('State').then(function (states) { return _this.states = states; });
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', forms_1.FormGroup)
    ], AddressComponent.prototype, "addressGroup", void 0);
    AddressComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'address-component',
            templateUrl: 'address.component.html',
            providers: [collection_service_1.CollectionService, validation_service_1.ValidationService]
        }), 
        __metadata('design:paramtypes', [validation_service_1.ValidationService, collection_service_1.CollectionService])
    ], AddressComponent);
    return AddressComponent;
}());
exports.AddressComponent = AddressComponent;
//# sourceMappingURL=address.component.js.map