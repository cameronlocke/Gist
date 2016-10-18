export class SearchCriteria {
    statusId : number;
    applicationTypeId : number;
    permitNo : string;
    submittedFrom : string;
    submittedTo : string;
    lastActionFrom : string;
    lastActionTo : string;
    decisionFrom : string;
    decisionTo : string;
    vehicleType : string;
    plateNo : string;
    vin : string;
    chassisNo : string;
    engineNo : string;
    vehiclePermitNo : string;
    designatedRoute : string;
    areaOfOperation : string;
    mapGeometry : string;
};

export class SearchResult {
    applicationId : number;
    applicationType : number;
    description : string;
    applicant : string;
    organisation : string;
    effectiveFrom : string;
    effectiveTo : string;
    status : string;
    permitId : string;
    locked : string;
    lockedBy : string;
    submissionDate : string;
    decisionDate : string;
    lastAction : string;

};




