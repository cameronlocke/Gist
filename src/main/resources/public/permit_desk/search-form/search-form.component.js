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
var collection_service_1 = require('../collection/collection.service');
var search_form_service_1 = require('../search-form/search-form.service');
var SearchFormComponent = (function () {
    // Inject FormBuilder and Validation Services
    function SearchFormComponent(_fb, validationService, searchFormService, collectionService) {
        this._fb = _fb;
        this.validationService = validationService;
        this.searchFormService = searchFormService;
        this.collectionService = collectionService;
        this.msgs = [];
    }
    SearchFormComponent.prototype.getSearchResults = function () {
        var _this = this;
        this.searchFormService.findSearchResults(this.searchForm.value).then(function (searchResults) { return _this.searchResults = searchResults; });
    };
    SearchFormComponent.prototype.getStatuses = function () {
        var _this = this;
        this.collectionService.findByCollection('Status').then(function (statuses) { return _this.statuses = statuses; });
        //       this.getCollection('Status');
        //       this.statuses = this.collections;
    };
    SearchFormComponent.prototype.getStates = function () {
        var _this = this;
        this.collectionService.findByCollection('State').then(function (states) { return _this.states = states; });
        //       this.getCollection('State');
        //       this.states = this.collections;
    };
    SearchFormComponent.prototype.getApplicationTypes = function () {
        var _this = this;
        this.collectionService.findByCollection('ApplicationType').then(function (applicationTypes) { return _this.applicationTypes = applicationTypes; });
        //       this.getCollection('ApplicationType');
        //       this.applicationTypes = this.collections;
    };
    SearchFormComponent.prototype.getVehicleTypes = function () {
        var _this = this;
        this.collectionService.findByCollection('VehicleType').then(function (vehicleTypes) { return _this.vehicleTypes = vehicleTypes; });
        //       this.getCollection('VehicleType');
        //       this.vehicleTypes = this.collections;    
    };
    // Initialise  
    SearchFormComponent.prototype.ngOnInit = function () {
        // Build the form (using model-driven approach)
        this.searchForm = this._fb.group({
            statusId: [0,],
            applicationType: [0,],
            permitNo: ["",],
            submittedFrom: ["",],
            submittedTo: ["",],
            lastActionFrom: ["",],
            lastActionTo: ["",],
            decisionFrom: ["",],
            decisionTo: ["",],
            authorisedFrom: ["",],
            authorisedTo: ["",],
            // Vehicle
            vehicleType: ["",],
            plateNo: ["",],
            plateState: ["",],
            vin: ["",],
            chassisNo: ["",],
            engineNo: ["",],
            // Geometry (route/area)
            designatedRoute: ["",],
            areaOfOperation: ["",],
            mapGeometry: ["",]
        });
        // Get first result set using initial criteria...
        this.getSearchResults();
        // Populate the drop-downs
        this.getStatuses();
        this.getStates();
        this.getApplicationTypes();
        this.getVehicleTypes();
        this.active = true;
    }; // OnInit
    // Events
    //    onSearch(): void {
    //TODO Show a spinny imgae thing, disable search page (perhaps a dialog?)
    //        console.log('Submitted value: ',  this.searchForm.value);  
    //        this.searchFormService.find SearchResults(this.searchForm.value).then(searchResults => this.searchResults = searchResults);
    //TODO Hide a spinny image thing, enable search page.
    //    }
    SearchFormComponent.prototype.onSubmit = function (value) {
        console.log('Submitted value: ', this.searchForm.value);
    };
    SearchFormComponent.prototype.onRowSelect = function (event) {
        console.log('Selected Row: ', event.applicationId);
        this.selectedResult = event;
        this.msgs = [];
        this.msgs.push({ severity: 'info', summary: 'Application ID#', detail: event.description });
    };
    //TODO display overlay panel
    //       overlaypanel.toggle(event);
    // TODO Ensure the following code adds keyboard navigation to PrimeNG datatable.
    // PrimeNG datagrid doesn't allow keybaord navigation.  Supposedly this code does so I need to figure out how to implement.
    // @HostListener('keydown.arrowUp', ['$event']) for iterating [(selection)] 
    //
    SearchFormComponent.prototype.onReset = function () {
        this.searchForm.reset();
        //       this.searchForm.controls.statusId.setValue(0);
        //       this.searchForm.controls.applicationType.setValue(0);
    };
    SearchFormComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'search-form',
            templateUrl: 'search-form.component.html',
            providers: [validation_service_1.ValidationService, collection_service_1.CollectionService, search_form_service_1.SearchFormService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder, validation_service_1.ValidationService, search_form_service_1.SearchFormService, collection_service_1.CollectionService])
    ], SearchFormComponent);
    return SearchFormComponent;
}());
exports.SearchFormComponent = SearchFormComponent;
//# sourceMappingURL=search-form.component.js.map