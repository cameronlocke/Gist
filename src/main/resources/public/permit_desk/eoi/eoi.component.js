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
var validation_service_1 = require('../validation/validation.service');
var EoIComponent = (function () {
    function EoIComponent(_fb, validationService) {
        this._fb = _fb;
        this.validationService = validationService;
    }
    EoIComponent.prototype.ngOnInit = function () {
        this.eoiGroup = this._fb.group({
            dateOfBirth: ["1966-10-24", forms_1.Validators.required],
            licenceNumber: ["123-24324-11", forms_1.Validators.required]
        });
    };
    __decorate([
        core_1.Input(), 
        __metadata('design:type', forms_1.FormGroup)
    ], EoIComponent.prototype, "eoiGroup", void 0);
    EoIComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'eoi-component',
            templateUrl: 'eoi.component.html',
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, validation_service_1.ValidationService])
    ], EoIComponent);
    return EoIComponent;
}());
exports.EoIComponent = EoIComponent;
//# sourceMappingURL=eoi.component.js.map