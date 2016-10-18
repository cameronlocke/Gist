import { Address } from '../address/address';
import { Contact } from '../contact/contact';
import { User } from '../user/user';

// Super-class (service by customerApplication service on server)
export class Registration {

  public application_no : number;
  businessName : string;
  public tradingName : string;
  public abn : string;
  public contactName : string;
  public contactPhone : string;
  public contactAfterHours : string;
  public contactEmail : string;
  public businessAddress : Address;
  public postalAddress : Address;
  public proprietor : Contact;
  public administrator : User;
  public termsAndConditions : Boolean;

}


