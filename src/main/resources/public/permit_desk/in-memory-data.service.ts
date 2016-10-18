import { InMemoryDbService } from 'angular2-in-memory-web-api';

export class InMemoryDataService implements InMemoryDbService {

  createDb() {
    let state = [
	{collection : 'State', key : "1", value: 'ACT' },
	{collection : 'State', key : "2", value: 'NSW' },
	{collection : 'State', key : "3", value: 'VIC' },
	{collection : 'State', key : "4", value: 'QLD' },
	{collection : 'State', key : "5", value: 'SA'  },
	{collection : 'State', key : "6", value: 'WA'  },
	{collection : 'State', key : "7", value: 'TAS' },
	{collection : 'State', key : "8", value: 'NT'  }
    ];

    let status = [
	{collection : 'Status', key : "0", value: 'All'  },
	{collection : 'Status', key : "1", value: 'Incomplete'  },
	{collection : 'Status', key : "2", value: 'Submitted' },
	{collection : 'Status', key : "3", value: 'In Progress' },
	{collection : 'Status', key : "4", value: 'Under Review' },
	{collection : 'Status', key : "5", value: 'Returned from Review' },
	{collection : 'Status', key : "6", value: 'Issued' },
	{collection : 'Status', key : "7", value: 'Rejected' },
	{collection : 'Status', key : "8", value: 'Returned' },
	{collection : 'Status', key : "9", value: 'Revoked' }
    ];

    let applicationtype = [
	{collection : 'ApplicationType', key : "0", value: 'All' },
	{collection : 'ApplicationType', key : "1", value: 'AHHO' },
	{collection : 'ApplicationType', key : "2", value: 'ATO' },
	{collection : 'ApplicationType', key : "3", value: 'Single Trip' },
	{collection : 'ApplicationType', key : "4", value: 'Area of Operation' },
	{collection : 'ApplicationType', key : "5", value: 'PBS' },
	{collection : 'ApplicationType', key : "6", value: 'B-Double 19m' },
	{collection : 'ApplicationType', key : "7", value: 'B-Double 23m' },
	{collection : 'ApplicationType', key : "8", value: 'B-Double 25m/26m)' },
	{collection : 'ApplicationType', key : "9", value: 'BAB Triple' },
	{collection : 'ApplicationType', key : "10", value: 'Road-Train - Type 1' },
	{collection : 'ApplicationType', key : "11", value: 'Road-Train - Type 2' }
    ];

    let vehicletype = [ 
	{collection : 'VehicleType', key : "0", value: 'All' },
	{collection : 'VehicleType', key : "1", value: 'Prime Mover' },
	{collection : 'VehicleType', key : "2", value: 'Semi-trailer' },
	{collection : 'VehicleType', key : "3", value: 'Low Loader' },
	{collection : 'VehicleType', key : "4", value: 'Dolly' },
	{collection : 'VehicleType', key : "5", value: 'Jinker'  },
	{collection : 'VehicleType', key : "6", value: 'SPV' },
	{collection : 'VehicleType', key : "7", value: 'SPV-Trailer' },
	{collection : 'VehicleType', key : "8", value: 'Agricultural' }
    ];


    let manufacturer = [ 
	{collection : 'Manufacturer', key : "0", value: 'Ford' },
	{collection : 'Manufacturer', key : "1", value: 'Isuzu' },
	{collection : 'Manufacturer', key : "2", value: 'Mack' },
	{collection : 'Manufacturer', key : "3", value: 'Peterbilt' },
    ];

    let sourceOfInformation = [ 
	{collection : 'SourceOfInformation', key : "0", value: 'Supervised Weigh' },
	{collection : 'SourceOfInformation', key : "1", value: 'Manufacturers plate' },
	{collection : 'SourceOfInformation', key : "2", value: 'Manufacturers letter' },
    ];

    let search = [
        {applicationId:1, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:2, applicationType:2, description:'ATO (JB1231/QLD)', applicant:'Mary Smith', organisation:'Marys Logistics', effectiveFrom:'2016/11/24',effectveTo:'2016/12/01',status:1,submissionDate:'2016/08/23',decisionDate:'2016/10/26',lastAction:'2016/10/26' },
        {applicationId:3, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:4, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:5, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:6, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:7, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:8, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:9, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:10, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
        {applicationId:11, applicationType:1, description:'AHHO', applicant:'Fred Bloggs', organisation:'Nick Trucks', effectiveFrom:'2016/10/24',effectveTo:'2016/11/01',status:1,permitId:'23432424',submissionDate:'2016/07/30',decisionDate:'2016/09/26',lastAction:'2016/09/26' },
    ];

    return {state, status, applicationtype, vehicletype, manufacturer, sourceOfInformation, search };
  }

}


