import { Contact } from '../contact/contact';
import { Address } from '../address/address';

export class Organisation {

  organisation_id : number;
  organisation_name : string;
  organisation_type : number;
  contact_id : Contact;
  address_id : Address;

}
