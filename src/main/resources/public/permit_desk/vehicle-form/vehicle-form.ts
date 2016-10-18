import { Vehicle } from '../vehicle/vehicle';
import { Dimension } from '../dimension/dimension';
import { AxleConfiguration } from '../axle-configuration/axle-configuration';
import { Permit } from '../permit/permit';

export class VehicleForm {

    public application_no : number;
    public vehicle : Vehicle;
    public dimension : Dimension;  // Only relevent to SPVs
    public axleConfiguration : AxleConfiguration
    public notes : string;
    public attachments  : string;
    public permit : Permit;

}



