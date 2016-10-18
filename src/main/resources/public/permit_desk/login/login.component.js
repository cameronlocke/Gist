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
var LoginComponent = (function () {
    function LoginComponent(_fb) {
        this._fb = _fb;
    }
    LoginComponent.prototype.ngOnInit = function () {
        this.error = false;
        this.loginForm = this._fb.group({
            username: ["",],
            password: ["",]
        });
    };
    LoginComponent.prototype.onLogin = function () {
        //    this.router.navigateByUrl("search");
    };
    LoginComponent = __decorate([
        core_1.Component({
            moduleId: module.id,
            selector: 'login-component',
            templateUrl: 'login.component.html',
            providers: [collection_service_1.CollectionService, validation_service_1.ValidationService]
        }), 
        __metadata('design:paramtypes', [forms_1.FormBuilder])
    ], LoginComponent);
    return LoginComponent;
}());
exports.LoginComponent = LoginComponent;
//# sourceMappingURL=login.component.js.map