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
var platform_browser_1 = require('@angular/platform-browser');
var forms_1 = require('@angular/forms');
var http_1 = require('@angular/http');
var router_1 = require('@angular/router');
// Imports for loading & configuring the in-memory web api
var angular2_in_memory_web_api_1 = require('angular2-in-memory-web-api');
var in_memory_data_service_1 = require('./in-memory-data.service');
// 3rd party components
var primeng_1 = require('primeng/primeng');
var primeng_2 = require('primeng/primeng');
var primeng_3 = require('primeng/primeng');
// Services
var validation_service_1 = require('./validation/validation.service');
var collection_service_1 = require('./collection/collection.service');
var registration_form_service_1 = require('./registration-form/registration-form.service');
var search_form_service_1 = require('./search-form/search-form.service');
var vehicle_form_service_1 = require('./vehicle-form/vehicle-form.service');
//import { RouteFormService } from './route-form/route-form.service';
//import { AreaFormService } from './area-form/area-form.service';
// Application Forms
var registration_form_component_1 = require('./registration-form/registration-form.component');
var search_form_component_1 = require('./search-form/search-form.component');
var vehicle_form_component_1 = require('./vehicle-form/vehicle-form.component');
// import { RouteFormComponent } from './route-form/route-form.component';
// import { AreaFormComponent } from './area-form/area-form.component';
// Pages
var landing_page_component_1 = require('./landing-page/landing-page.component');
var login_component_1 = require('./login/login.component');
//import { VehicleBuilder } from './vehicle-builder/vehicle-builder.component';
//import { Route } from './route/route'; // -probably service only
//import { Area } from './area/area';
//import { Conditions } from './conditions/conditions';
//import { AutoConditions } from './auto-conditions/auto-conditions';
//import { OfficeManagement } from './office-management/office-management'; // Includes users, business address and contact etc.
//import { ProfileManagement } from './profile-management/profile-management'; // Self-management incl passwords (name change etc)
//import { DashBoard } from './dashboard/dashboard';
// Components
var page_header_component_1 = require('./page-header/page-header.component');
var page_title_component_1 = require('./page-title/page-title.component');
var page_footer_component_1 = require('./page-footer/page-footer.component');
//import { MessageComponent } from './message/message.component';  - TBD
var address_component_1 = require('./address/address.component');
var contact_component_1 = require('./contact/contact.component');
var user_component_1 = require('./user/user.component');
var eoi_component_1 = require('./eoi/eoi.component');
var dimension_component_1 = require('./dimension/dimension.component');
var axle_component_1 = require('./axle/axle.component');
var search_actions_component_1 = require('./search-form/search-actions.component'); //Actions need to be form specific - to be removed
//import { DimensionComponent } from './dimension/dimension.component';
//import { AxleComponent } from './axle/axle.component';
//import { VehicleCombinationComponent } from './vehicle-combination/vehicle-combination.component';
//import { MapComponent } from './map/map.component';
var validation_messages_component_1 = require('./validation/validation-messages.component');
var AppModule = (function () {
    function AppModule() {
    }
    AppModule = __decorate([
        core_1.NgModule({
            imports: [
                platform_browser_1.BrowserModule,
                forms_1.FormsModule,
                forms_1.ReactiveFormsModule,
                http_1.HttpModule,
                angular2_in_memory_web_api_1.InMemoryWebApiModule.forRoot(in_memory_data_service_1.InMemoryDataService),
                primeng_1.PanelModule,
                primeng_1.OverlayPanelModule,
                primeng_1.TabViewModule,
                primeng_2.CheckboxModule,
                primeng_2.ButtonModule,
                primeng_2.DropdownModule,
                primeng_3.GrowlModule,
                primeng_3.FileUploadModule,
                primeng_3.DataTableModule,
                primeng_3.SharedModule,
                primeng_2.MenuModule,
                router_1.RouterModule.forRoot([
                    { path: '', redirectTo: 'login', pathMatch: 'full' },
                    { path: 'index', component: login_component_1.LoginComponent },
                    { path: 'login', component: login_component_1.LoginComponent },
                    { path: 'signup', component: registration_form_component_1.RegistrationFormComponent },
                    { path: 'home', component: search_form_component_1.SearchFormComponent },
                    { path: 'search', component: search_form_component_1.SearchFormComponent },
                    { path: 'vehicle', component: vehicle_form_component_1.VehicleFormComponent }
                ])
            ],
            declarations: [
                landing_page_component_1.LandingPageComponent,
                login_component_1.LoginComponent,
                registration_form_component_1.RegistrationFormComponent,
                search_form_component_1.SearchFormComponent,
                vehicle_form_component_1.VehicleFormComponent,
                page_header_component_1.PageHeaderComponent,
                page_title_component_1.PageTitleComponent,
                page_footer_component_1.PageFooterComponent,
                address_component_1.AddressComponent,
                contact_component_1.ContactComponent,
                user_component_1.UserComponent,
                eoi_component_1.EoIComponent,
                dimension_component_1.DimensionComponent,
                axle_component_1.AxleComponent,
                search_actions_component_1.SearchActionsComponent,
                validation_messages_component_1.ValidationMessagesComponent
            ],
            providers: [
                validation_service_1.ValidationService,
                collection_service_1.CollectionService,
                registration_form_service_1.RegistrationFormService,
                search_form_service_1.SearchFormService,
                vehicle_form_service_1.VehicleFormService
            ],
            bootstrap: [
                landing_page_component_1.LandingPageComponent
            ]
        }), 
        __metadata('design:paramtypes', [])
    ], AppModule);
    return AppModule;
}());
exports.AppModule = AppModule;
//# sourceMappingURL=app.module.js.map