import { Injectable } from '@angular/core';
import { Address } from './address';
import { ADDRESSES } from './address.mock';

@Injectable()
export class AddressService {

  findAddress(): Promise<Address> { 

    return Promise.resolve(ADDRESSES[1]);

  } 

//  This will need a bit of work.
  findAddressByStartWithFullAddress (value : string) : Promise<Address[]> {

    return Promise.resolve(ADDRESSES);

  }

}

