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
var http_1 = require('@angular/http');
require('rxjs/add/operator/toPromise');
var registration_form_1 = require('../registration-form/registration-form');
var RegistrationFormService = (function () {
    function RegistrationFormService(http) {
        this.http = http;
        this.overloadUrl = 'app/heroes'; // URL to web api
    }
    RegistrationFormService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // for demo purposes only
        return Promise.reject(error.message || error);
    };
    RegistrationFormService.prototype.save = function (registrationForm) {
        // Send RegistrationForm to server
        console.log(JSON.stringify(registration_form_1.RegistrationForm));
        return this.http.get(this.overloadUrl)
            .toPromise()
            .then(function (response) { return response.json().data; })
            .catch(this.handleError);
    };
    RegistrationFormService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], RegistrationFormService);
    return RegistrationFormService;
}());
exports.RegistrationFormService = RegistrationFormService;
//# sourceMappingURL=registration-form.service.js.map