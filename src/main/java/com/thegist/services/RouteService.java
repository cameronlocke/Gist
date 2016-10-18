package com.thegist.services;

import com.thegist.model.RouteApplication;

import java.util.List;

// These are the services a conroller will call to perform operations on the 
// CustomerApplcaiton mega-object.

//This mega-class is an exploded version of the ApplicationCustomer entity.
//Contains populated data for all linked columns linked to ApplcaitonCustomer table
//so that a single service can be used to support a Single Page application rather
//in AngularJS (without needing to expose low level domain repositories.
//Furthermore, All business logic will be performed within the service layer (com.thegist.services)
//including validation and multi-object processing.

// All controllers must only call services in this layer. Only the Controllers will be exposed as Restful 
// web-services rather than model repositories.  This avoids low-level data-access methods 
// being over-exposed.


// Not sure how Spring Security fits into all this.  A job for Chief Technology Officer ???

//TODO Not sure this is the best practice (or even if it will work) - Cam ?


public interface RouteService {

	public List<RouteApplication> findAll(); 

	public List<RouteApplication> findByApplicationId(Integer id);
	
	public RouteApplication findOne(Integer id);

	public RouteApplication save(RouteApplication customerApplication);
	
}
