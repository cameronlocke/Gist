import { FormControl } from '@angular/forms';

export class ValidationService {

    static eMail(control: FormControl) {


      // RFC 2822 compliant regex
        if ( (control.value != "") &&  
           (!control.value.match(/[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?/))) {
            return { 'invalidEmailAddress': true };
        }
 
        return null;
    }
 

    static postcode(control: FormControl) {

        const POSTCODE_REGEXP = /^[0-9]{4}$/

        if ( (control.value != "") && 
           (!control.value.match(POSTCODE_REGEXP)) ) {
            return { "invalidPostcode": false };
        }
        return null;
    }

    static phone(control: FormControl) {

        const PHONE_REGEXP = /^[0-9]{10}$/

        if ( (control.value != "") && 
           (!control.value.match(PHONE_REGEXP)) ) {
            return { "invalidPhone": false };
        }
        return null;
    }


    static username(control: FormControl) {

    // letters and digits only...username case insensitive

       const USERNAME_REGEXP = /^[a-z,A-Z][0-9a-zA-Z.]*$/

        if ( (control.value != "") && 
           (!control.value.match(USERNAME_REGEXP)) ) {
           return { "invalidUserName": false };
        }
        return null;
    }

       
    static personname(control: FormControl) {

    // letters, hyphens and space only...username case insensitive

        const NAME_REGEXP = /^[a-z ,.'-]+$/i

        if ( (control.value != "") && 
           (!control.value.match(NAME_REGEXP)) ) {
           return { "invalidName": false };
        }
        return null;
    }



    static usernameDuplicate(control: FormControl) {

    // TODO Implement  (will need to be a service call)

	//  return { "duplicateUserName": false };

        return null;

    }


    static password(control: FormControl) { 

    // Password rules determined by the organisation.
    // Min length is habdle by minLength validator but  
    // may also need Upper/lower case, special characeter, AT one number and one letter etc
    // or even havent uused in last 10 reset or last 6 months (service call)

        if (control.value.length <= 6 ) {
            return { "invalidPassword": true };
        }

        return null;

    }


    static abn(control: FormControl) { 

    // TODO Implement

	//  return { "invalidABN": false };
        return null;

    }


    static acn(control: FormControl) { 

    // TODO Implement

	//  return { "invalidACN": false };

        return null;

    }

    static creditCardNo(control: FormControl) { 

        const CARD_NO_REGEX = /^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/

       if (CARD_NO_REGEX.test(control.value)) {
            return { "invalidCreditCard": true };
       }

       return null;
    }
   

    static creditCardExpiry(control: FormControl) { 

         // Visa, MasterCard, American Express, Diners Club, Discover, JCB
        if (control.value.match(/^(?:4[0-9]{12}(?:[0-9]{3})?|5[1-5][0-9]{14}|6(?:011|5[0-9][0-9])[0-9]{12}|3[47][0-9]{13}|3(?:0[0-5]|[68][0-9])[0-9]{11}|(?:2131|1800|35\d{3})\d{11})$/)) {
            return null;
        } else {
            return { 'invalidCreditCard': true };
        }

   }

    static tickRequired(control: FormControl) { 

	//TODO implement
        if (control.value != true ) {
           return { 'tickRequired':true };
        }

        return null;

    }

    static getValidatorErrorMessage(validatorName: string, validatorValue?: any) {
        let messages = {
            'required'           : 'Required',
            'invalidEmailAddress': 'Invalid email address',
            'invalidPostcode'    : 'Invalid postcode',
            'invalidUsername'    : 'Username is invalid. Must consist of only letters and number',
            'duplicateUsername'  : 'Username already exists',
            'invalidPassword'    : 'Invalid password. Password must be at least 6 characters long, and contain a number.',
            'invalidABN'         : 'Invalid ABN',
            'invalidACN'         : 'Invalid ACN',
            'invalidCreditCard'  : 'Invalid credit card number',
            'minlength'          : `Minimum length ${validatorValue.requiredLength}`,
            'tickRequired'       : 'Required',
            'invalidPhone'       : 'Invalid phone number, must be 10 digits',
            'invalidName'        : 'Invalid name'


        };

        return messages[validatorName];
    }



}




