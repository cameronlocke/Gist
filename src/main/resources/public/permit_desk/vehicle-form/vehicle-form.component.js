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
var common_1 = require('@angular/common');
var forms_1 = require('@angular/forms');
var router_1 = require('@angular/router');
var validation_service_1 = require('../validation/validation.service');
var collection_service_1 = require('../collection/collection.service');
var vehicle_form_service_1 = require('../vehicle-form/vehicle-form.service');
var VehicleFormComponent = (function () {
    // Inject FormBuilder and Validation Services
    function VehicleFormComponent(_fb, validationService, collectionService, route, location, vehicleFormService) {
        this._fb = _fb;
        this.validationService = validationService;
        this.collectionService = collectionService;
        this.route = route;
        this.location = location;
        this.vehicleFormService = vehicleFormService;
        //TODO This should be "mapped" to model.
        this.attachments = [];
    }
    VehicleFormComponent.prototype.getVehicleTypes = function () {
        var _this = this;
        this.collectionService.findByCollection('VehicleType').then(function (vehicleTypes) { return _this.vehicleTypes = vehicleTypes; });
    };
    VehicleFormComponent.prototype.getStates = function () {
        var _this = this;
        this.collectionService.findByCollection('State').then(function (states) { return _this.states = states; });
    };
    VehicleFormComponent.prototype.getManufacturers = function () {
        var _this = this;
        this.collectionService.findByCollection('Manufacturer').then(function (manufacturers) { return _this.manufacturers = manufacturers; });
    };
    VehicleFormComponent.prototype.getSources = function () {
        var _this = this;
        this.collectionService.findByCollection('SourceOfInformation').then(function (sources) { return _this.sources = sources; });
    };
    // Initialise  
    VehicleFormComponent.prototype.ngOnInit = function () {
        // Build the form (using model-driven approach)
        this.vehicleForm = this._fb.group({
            applicationId: [{ value: "", disabled: true },],
            vehicleType: ["1",],
            plate: ["882FBN",],
            plateState: ["4",],
            vin: ["123dds23424c",],
            chassisNo: ["243vcdsc",],
            engineNo: ["324535",],
            manufacturer: ["2",],
            model: ["BT50",],
            noOfAxles: [0,],
            tare: ["9.25",],
            gvm: ["26.5",],
            gcm: ["80.5",],
            sourceOfInformation: ["2",],
            dimension: this._fb.group({
                width: ["3.5",],
                height: ["3.3",],
                length: ["14.5",],
                frontOverhang: ["1.2",],
                rearOverhang: ["1.8",],
                frontProjection: ["2.1",],
                rearProjection: ["10.5",],
                lhsProjection: ["1.0",],
                rhsProjection: ["0.0",]
            }),
            axleConfiguration: this._fb.group({
                noOfAxles: [,],
                grossMass: [,]
            }),
            axles: this._fb.array([this.initAxle(1),]),
            notes: ["",],
            // Somehow need to bind attachments string array to form control (so can be sent to B/E on submit
            //		attachments     : this._fb.array(["",]),
            permitNo: ["",] // ATO Number, only available if has been issued
        });
        // Form created so load if application_id passed.
        //	this.route.params.forEach((params: Params) => {
        //		let id = +params['id'];
        //      	if ((id != null) && (id != 0 )) {
        //			this.vehicleFormService.findOne(id).then(vehicleForm => this.vehicleForm.value = vehicleForm);
        //		}
        // If formMode is Edit or Show then populate the page...
        // The form is defined initally as READ-ONLY/DISABLED, however if edit or create then make editable. 
        // Populate the drop-downs
        this.getVehicleTypes();
        this.getStates();
        this.getManufacturers();
        this.getSources(); // This may be a bunch of check-boxes as can have multiple.
        //TODO Do I pass the form mode as a parameter in the URL,
        // or I create a "mode" service (take application and user info to determine read-only or not )
        //
        //		let mode = params['mode'];
        //		if ((mode = "create" ) || (mode = "edit" )) {
        //	                this.vehicleForm.prop.readonly('readonly',false);
        //	        } else {
        //	                this.vehicleForm.prop.readonly('readonly',true);
        //		}
        //	});
        this.active = true;
    }; // OnInit
    VehicleFormComponent.prototype.initAxle = function (axleNo) {
        return this._fb.group({
            axleNo: [axleNo,],
            mass: [, forms_1.Validators.required],
            noOfTyres: [, forms_1.Validators.required],
            tyreWidth: [, forms_1.Validators.required],
            groundContactLength: [, forms_1.Validators.required],
            groundContactWidth: [, forms_1.Validators.required]
        });
    };
    // This will be call the number of axles in the combination (summed form vehicleCombination).
    VehicleFormComponent.prototype.addAxle = function (axleNo) {
        // add axle to the list
        var control = this.vehicleForm.controls['axles'];
        control.push(this.initAxle(axleNo));
    };
    VehicleFormComponent.prototype.removeAxle = function (i) {
        // remove axle from the list, not sure if this is used or we remove all.
        var control = this.vehicleForm.controls['axles'];
        control.removeAt(i);
    };
    VehicleFormComponent.prototype.clearAxles = function () {
        // pop-off till no more
        var control = this.vehicleForm.controls['axles'];
        for (var i = control.length; i > 0; i--) {
            control.removeAt(i);
        }
    };
    // Events
    VehicleFormComponent.prototype.onReadOnly = function (value) {
        //TODO Make all form read-only/disabled or enabled.
        // Can't just remove read-only attributes as requires DOM property manipulation.
    };
    VehicleFormComponent.prototype.onSubmit = function (value) {
        //TODO Show a spinny image thing, disable page
        // this.vehcileFormService.save(this.vehicleForm.value).then(vehicleForm => this.vehicleForm = vehicleForm);
        //TODO Hide a spinny image thing, enable page.
        // Process/display errors.  If All OK return to home or go to payment page for delivery (if fees outstanding)
        console.log('Submitted value: ', value);
    };
    VehicleFormComponent.prototype.onReset = function () {
        //TODO Reset form to initialisation state.
        this.vehicleForm.reset();
    };
    VehicleFormComponent.prototype.goBack = function () {
        console.log('Location: ', this.location.path);
        this.location.back();
    };
    VehicleFormComponent.prototype.onChangeAxles = function () {
        var i;
        var j = this.vehicleForm.controls['noOfAxles'].value;
        console.log('No Of Axles: ', this.vehicleForm.controls['noOfAxles'].value);
        this.clearAxles();
        for (i = 0; i < j; i++) {
            this.addAxle(i + 1);
        }
    };
    VehicleFormComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'vehicle-form',
            templateUrl: 'vehicle-form.component.html',
            providers: [validation_service_1.ValidationService, collection_service_1.CollectionService, vehicle_form_service_1.VehicleFormService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, validation_service_1.ValidationService, collection_service_1.CollectionService, router_1.ActivatedRoute, common_1.Location, vehicle_form_service_1.VehicleFormService])
    ], VehicleFormComponent);
    return VehicleFormComponent;
}());
exports.VehicleFormComponent = VehicleFormComponent;
//# sourceMappingURL=vehicle-form.component.js.map