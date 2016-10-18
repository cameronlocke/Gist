import { Vehicle} from '../vehicle/vehicle';
import { Dimension } from '../dimension/dimension';
import { AxleConfiguration } from '../axle-configuration/axle-configuration';

export class VehicleCombination {

  public vehicleCombinationId : number;
  public vehicles : Vehicle[];
  public dimension : Dimension;
  public axleConfiguration : AxleConfiguration;
  
};
