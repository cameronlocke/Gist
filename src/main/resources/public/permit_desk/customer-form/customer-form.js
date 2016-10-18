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
// ALL ADMIN pages should be some sort of find / search / list
// And then overlay dialog to edit (with OK and cancel).
var SelfRegistrationFormComponent = (function () {
    // Inject FormBuilder and Validation Services
    function SelfRegistrationFormComponent(_fb, validationService) {
        this._fb = _fb;
        this.validationService = validationService;
    }
    // Initialise  
    SelfRegistrationFormComponent.prototype.ngOnInit = function () {
        // Build the form (using model-driven approach)
        this.searchForm = this._fb.group({
            customerName: ["",],
            customerPermitNo: ["",],
            customerABN: ["",],
            customerACN: ["",],
        });
    }; // OnInit
    // Events
    SelfRegistrationFormComponent.prototype.onSubmit = function (value) {
        console.log('Submitted value: ', value);
    };
    SelfRegistrationFormComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'customer-form',
            templateUrl: 'customer-form.html',
            providers: [validation_service_1.ValidationService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, validation_service_1.ValidationService])
    ], SelfRegistrationFormComponent);
    return SelfRegistrationFormComponent;
}());
exports.SelfRegistrationFormComponent = SelfRegistrationFormComponent;
//# sourceMappingURL=customer-form.js.map