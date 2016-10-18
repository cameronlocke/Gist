import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule, FormControl } from '@angular/forms';
import { HttpModule }    from '@angular/http';
import { RouterModule }   from '@angular/router';

// Imports for loading & configuring the in-memory web api
import { InMemoryWebApiModule } from 'angular2-in-memory-web-api';
import { InMemoryDataService }  from './in-memory-data.service';

// 3rd party components
import { PanelModule, TabViewModule, OverlayPanelModule } from 'primeng/primeng';
import { CheckboxModule, ButtonModule, DropdownModule, MenuModule } from 'primeng/primeng';
import { FileUploadModule, DataTableModule, SharedModule, GrowlModule } from 'primeng/primeng';
import { Message, SelectItem, MenuItem } from 'primeng/primeng';

// Services
import { ValidationService } from './validation/validation.service';
import { CollectionService } from './collection/collection.service';
import { RegistrationFormService } from './registration-form/registration-form.service';
import { SearchFormService } from './search-form/search-form.service';
import { VehicleFormService } from './vehicle-form/vehicle-form.service';
//import { RouteFormService } from './route-form/route-form.service';
//import { AreaFormService } from './area-form/area-form.service';

// Application Forms
import { RegistrationFormComponent } from './registration-form/registration-form.component';
import { SearchFormComponent } from './search-form/search-form.component';
import { VehicleFormComponent } from './vehicle-form/vehicle-form.component';
// import { RouteFormComponent } from './route-form/route-form.component';
// import { AreaFormComponent } from './area-form/area-form.component';

// Pages
import { LandingPageComponent } from './landing-page/landing-page.component';
import { LoginComponent } from './login/login.component';
//import { VehicleBuilder } from './vehicle-builder/vehicle-builder.component';

//import { Route } from './route/route'; // -probably service only
//import { Area } from './area/area';

//import { Conditions } from './conditions/conditions';
//import { AutoConditions } from './auto-conditions/auto-conditions';
//import { OfficeManagement } from './office-management/office-management'; // Includes users, business address and contact etc.
//import { ProfileManagement } from './profile-management/profile-management'; // Self-management incl passwords (name change etc)
//import { DashBoard } from './dashboard/dashboard';

// Components
import { PageHeaderComponent } from './page-header/page-header.component';
import { PageTitleComponent } from './page-title/page-title.component';
import { PageFooterComponent } from './page-footer/page-footer.component';
//import { MessageComponent } from './message/message.component';  - TBD
import { AddressComponent } from './address/address.component';
import { ContactComponent } from './contact/contact.component';
import { UserComponent } from './user/user.component';
import { EoIComponent } from './eoi/eoi.component';
import { DimensionComponent } from './dimension/dimension.component';
import { AxleComponent } from './axle/axle.component';


import { SearchActionsComponent } from './search-form/search-actions.component'; //Actions need to be form specific - to be removed

//import { DimensionComponent } from './dimension/dimension.component';
//import { AxleComponent } from './axle/axle.component';
//import { VehicleCombinationComponent } from './vehicle-combination/vehicle-combination.component';
//import { MapComponent } from './map/map.component';

import { ValidationMessagesComponent } from './validation/validation-messages.component';

@NgModule({
  imports: 	[ 
		BrowserModule, 
		FormsModule, 
		ReactiveFormsModule, 	
		HttpModule, 
		InMemoryWebApiModule.forRoot(InMemoryDataService), 
		PanelModule, 
                OverlayPanelModule,
		TabViewModule, 
		CheckboxModule, 
		ButtonModule, 
		DropdownModule, 
		GrowlModule, 
		FileUploadModule, 
		DataTableModule, 
		SharedModule,
		MenuModule,
        RouterModule.forRoot([
		{ path: '', redirectTo: 'login', pathMatch: 'full'},
	        { path: 'index', component: LoginComponent },
	        { path: 'login', component: LoginComponent },
            	{ path: 'signup', component: RegistrationFormComponent },
	        { path: 'home', component: SearchFormComponent },
	        { path: 'search', component: SearchFormComponent },
	        { path: 'vehicle', component: VehicleFormComponent }
//	        { path: 'route', component: RouteFormComponent }
//	        { path: 'area', component: AreaFormComponent }
		])
		],
  declarations: [
        LandingPageComponent,  
        LoginComponent,
		RegistrationFormComponent,
		SearchFormComponent, 
		VehicleFormComponent,
		PageHeaderComponent, 
		PageTitleComponent,
		PageFooterComponent, 
		AddressComponent,  
		ContactComponent, 
		UserComponent, 
		EoIComponent,
		DimensionComponent,
		AxleComponent,
		SearchActionsComponent,
		ValidationMessagesComponent
		],
  providers :   [
		ValidationService,
		CollectionService,
		RegistrationFormService,
		SearchFormService,
		VehicleFormService
		],
  bootstrap:    [
		LandingPageComponent
		]
})

export class AppModule { } 


