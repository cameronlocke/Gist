import { Contact } from '../contact/contact';
import { Organisation } from '../organisation/organisation';

export class User {

  user_id : number;
  user_name : string;
  contact : Contact;
  organisation : Organisation;

}
