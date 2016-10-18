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
var search_form_1 = require('./search-form');
var SearchFormService = (function () {
    function SearchFormService(http) {
        this.http = http;
        this.url = 'app/search';
    }
    SearchFormService.prototype.findSearchResults = function (searchCriteria) {
        console.log(JSON.stringify(search_form_1.SearchCriteria));
        return this.http.get(this.url)
            .toPromise()
            .then(function (response) { return response.json().data; })
            .catch(this.handleError);
    };
    SearchFormService.prototype.handleError = function (error) {
        console.error('An error occurred', error); // TODO need to productionify this, for demo purposes only
        return Promise.reject(error.message || error);
    };
    SearchFormService = __decorate([
        core_1.Injectable(), 
        __metadata('design:paramtypes', [http_1.Http])
    ], SearchFormService);
    return SearchFormService;
}());
exports.SearchFormService = SearchFormService;
//# sourceMappingURL=search-form.service.js.map