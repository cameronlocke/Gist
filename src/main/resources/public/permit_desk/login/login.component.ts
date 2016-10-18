import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { Collection } from '../collection/collection';
import { CollectionService } from '../collection/collection.service';
import { ValidationService } from '../validation/validation.service';

@Component  ({
  moduleId    : module.id,
  selector    : 'login-component',
  templateUrl : 'login.component.html',
  providers   : [CollectionService, ValidationService ]
})


export class LoginComponent implements OnInit {

  loginForm : FormGroup;

  private error : boolean;

  constructor (private _fb: FormBuilder) {}
    
  ngOnInit() : void {

    this.error = false;
      
    this.loginForm = this._fb.group ({
        username : ["", ],
        password : ["", ]
    });
  
  }

  onLogin() {
//    this.router.navigateByUrl("search");
  }

}
