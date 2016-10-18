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
var registration_form_service_1 = require('../registration-form/registration-form.service');
var RegistrationFormComponent = (function () {
    // Inject FormBuilder and Validation Services
    function RegistrationFormComponent(_fb, validationService, registrationFormService) {
        this._fb = _fb;
        this.validationService = validationService;
        this.registrationFormService = registrationFormService;
        this.uploadedFiles = [];
    }
    // Initialise  
    RegistrationFormComponent.prototype.ngOnInit = function () {
        this.submitted = false;
        this.active = true;
        // Build the form (using model-driven approach)
        this.selfRegistrationForm = this._fb.group({
            businessIdentity: this._fb.group({
                businessName: ["theGISt", forms_1.Validators.required],
                tradingName: ["Overload", forms_1.Validators.required],
                abn: ["123-456-78", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.abn])],
                contact: this._fb.group({
                    firstName: ["B-Firstname", forms_1.Validators.required],
                    middleName: ["B-Middle",],
                    lastName: ["B-Surname", forms_1.Validators.required],
                    phone: ["0755613342", forms_1.Validators.required],
                    email: ["business@testing.com", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.eMail])]
                }),
                afterHoursPhone: ["0799999999", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.phone])]
            }),
            businessAddress: this._fb.group({
                addressSearch: ["",],
                addressLine1: ["B-Line1", forms_1.Validators.required],
                addressLine2: ["B-line2",],
                addressSuburb: ["B-Suburb", forms_1.Validators.required],
                addressState: [3, forms_1.Validators.required],
                addressPostcode: ["3214", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.postcode])]
            }),
            sameAsBusinessAddress: [true,],
            postalAddress: this._fb.group({
                addressSearch: ["",],
                addressLine1: ["P-Line1", forms_1.Validators.required],
                addressLine2: ["P-line2",],
                addressSuburb: ["P-Suburb", forms_1.Validators.required],
                addressState: [4, forms_1.Validators.required],
                addressPostcode: ["4009", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.postcode])]
            }),
            delegate: this._fb.group({
                contact: this._fb.group({
                    firstName: ["D-Firstname", forms_1.Validators.required],
                    middleName: ["",],
                    lastName: ["D-Surname", forms_1.Validators.required],
                    phone: ["0755613342", forms_1.Validators.required],
                    email: ["delgate@testing.com", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.eMail])]
                }),
                eoi: this._fb.group({
                    dateOfBirth: ["1966-10-24", forms_1.Validators.required],
                    licenceNumber: ["123-24324-11", forms_1.Validators.required]
                })
            }),
            sameAsProprietor: [false,],
            administrator: this._fb.group({
                user: this._fb.group({
                    userName: ["nickw", forms_1.Validators.required],
                    contact: this._fb.group({
                        firstName: ["A-Firstname", forms_1.Validators.required],
                        middleName: ["",],
                        lastName: ["A-Surname", forms_1.Validators.required],
                        phone: ["0755613342", forms_1.Validators.required],
                        email: ["admin@testing.com", forms_1.Validators.compose([forms_1.Validators.required, validation_service_1.ValidationService.eMail])]
                    }),
                    eoi: this._fb.group({
                        dateOfBirth: ["1966-10-24", forms_1.Validators.required],
                        licenceNumber: ["123-24324-11", forms_1.Validators.required]
                    })
                })
            }),
            notes: ["",],
            termsAndConditions: ["", validation_service_1.ValidationService.tickRequired]
        });
    }; // OnInit
    // Events
    RegistrationFormComponent.prototype.onSubmit = function (value) {
        this.submitted = true;
        console.log('Submitted value: ', value);
        console.log(JSON.stringify(this.selfRegistrationForm.value));
    };
    // Attachments are not uploaded through INPUT control.
    RegistrationFormComponent.prototype.onUpload = function (event) {
        for (var _i = 0, _a = event.files; _i < _a.length; _i++) {
            var file = _a[_i];
            this.uploadedFiles.push(file);
        }
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'File Uploaded', detail: '' });
    };
    RegistrationFormComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'registration-form',
            templateUrl: 'registration-form.component.html',
            providers: [validation_service_1.ValidationService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, validation_service_1.ValidationService, registration_form_service_1.RegistrationFormService])
    ], RegistrationFormComponent);
    return RegistrationFormComponent;
}());
exports.RegistrationFormComponent = RegistrationFormComponent;
//# sourceMappingURL=registration-form.component.js.map