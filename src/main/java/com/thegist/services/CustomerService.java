package com.thegist.services;

import com.thegist.model.CustomerApplication;

import java.util.List;

// These are the services a controller will call to perform operations on the 
// CustomerApplcaiton mega-object.

//This mega-class is an exploded version of the ApplicationCustomer entity.
//Contains populated data for all linked columns linked to ApplicationCustomer table
//so that a single service can be used to support a Single Page application 
//in AngularJS (without needing to expose low level domain repositories).
//Furthermore, all business logic will be performed within the service layer (com.thegist.services)
//including cross-entity validation and multi-object processing (e.g. analysis).

// All controllers must only call services in this layer. Only these Controllers will be exposed as Restful 
// web-services rather than model repositories.  This avoids low-level data-access methods 
// being exposed.


// Not sure how Spring Security fits into all this.  A job for Chief Technology Officer ???

//TODO Not sure this is the best practice (or even if it will work) - Cam/CTO ?

public interface CustomerService {

	public List<CustomerApplication> findAll(); 

	public CustomerApplication findOne(Integer customerApplicationId);

	public CustomerApplication create(CustomerApplication customer);

	public CustomerApplication update(CustomerApplication customer);
	
	public Void delete(CustomerApplication customer);

}
