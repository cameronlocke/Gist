package com.thegist.services;

import com.thegist.domain.AddressRepository;
import com.thegist.domain.Application;
import com.thegist.domain.ApplicationCustomer;
import com.thegist.domain.ApplicationCustomerRepository;
import com.thegist.domain.ApplicationRepository;
import com.thegist.domain.ContactRepository;
import com.thegist.domain.Organisation;
import com.thegist.domain.OrganisationRepository;
import com.thegist.domain.UserRepository;
import com.thegist.model.CustomerApplication;
import com.thegist.utils.messages.Error;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

// These are the services a controller will call to perform operations on the 
// CustomerApplication mega-object.

//This mega-class is an exploded version of the ApplicationCustomer entity.
//Contains populated data for all linked columns linked to ApplicationCustomer table
//so that a single service can be used to support a Single Page application 
//in AngularJS (without needing to expose low level domain repositories).
//Furthermore, all business logic will be performed within the service layer (com.thegist.services)
//including cross-entity validation and multi-object processing (e.g. analysis).

// All controllers must only call services in this layer. Only these Controllers will be exposed as Restful 
// web-services rather than model repositories.  This avoids low-level data-access methods 
// being exposed.

//TODO Not sure this is the best practice (or even if it will work) - Over to CTO ?

//TODO Not sure how Spring Security fits into all this.  
// - Create/save method is "public" as user not -authenticated but method should still be secured (at least to the client app)
// Another job for Chief Technology Officer ?


public class CustomerServiceImpl implements CustomerService {

	// The following values are not specific to.overload core and as such are
	// stored in the extra (Hstore) column using the following keys : 
	public static final String BUSINESSNAME = "Business Name";
	public static final String TRADINGNAME = "Trading Name";
	public static final String ABN = "ABN";
	public static final String OWNERLIST = "Owners";
	public static final String LICENCE = "Licence";
	public static final String DATEOFBIRTH = "DateoOfBirth";
	
	
	@Autowired
	ApplicationCustomerRepository applicationCustomerRepository;

	@Autowired
	ApplicationRepository applicationRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	ContactRepository contactRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	OrganisationRepository organisationRepository;
		
	// Returns all versions of all applications.  Expensive query and will not be used in UI.
	public List<CustomerApplication> findAll() {

		List<CustomerApplication> result = new ArrayList<CustomerApplication>();
		while (applicationCustomerRepository.findAll().iterator().hasNext()) {
			result.add(populateEntity(applicationCustomerRepository.findAll().iterator().next()));
		}
		return result;
	}

	@Override
	public CustomerApplication findOne(Integer customerApplicationId) {
		return populateEntity(applicationCustomerRepository.findOne(customerApplicationId));
	}

	@Override
	public CustomerApplication create(CustomerApplication customerApplication) {
		
		// WILL THIS WORK ?????? OR IS HIBERNATE TOO SMART. 
		//  i.e. Since it knows the entity-relationships will it try to save all the sub-entities as 
		// part of the applicationCustomer save or do I need to manually create all the sub-entities
		// so that the customerApplicaiotn entity can be saved ? 
		// I working based on the latter, that the ApplicationCustomer entity has constraints that
		// rely on sub-entities being in place first.
		
		customerApplication = validate(customerApplication);
		if (!customerApplication.getErrors().isEmpty()) {
			// need to return some network error code 404?
			return customerApplication;
		}
		
		// Save all the sub-entities first (otherwise postgresql will have a hissy fit).
		customerApplication.setBusinessContact(contactRepository.save(customerApplication.getBusinessContact()));
		customerApplication.setBusinessAddress(addressRepository.save(customerApplication.getBusinessAddress()));
		customerApplication.setPostalAddress(addressRepository.save(customerApplication.getPostalAddress()));
		
        // Delegate = Contact with EOI info.		
		if ( customerApplication.isSameAsBusinessContact() )  {
    	   customerApplication.getDelegateContact().setContactId(customerApplication.getBusinessContact().getContactId());
	    }
        customerApplication.getDelegateContact().getExtra().put(LICENCE, customerApplication.getDelegateLicenceNo().toString());
        customerApplication.getDelegateContact().getExtra().put(DATEOFBIRTH, customerApplication.getDelegateDateOfBirth().toString());
		customerApplication.setDelegateContact(contactRepository.save(customerApplication.getDelegateContact()));
		
		// Create a new Organisation (a customer is just an external organisation)
		// Now we hook-up various bits from the application to create an organisation 
		Organisation organisation = new Organisation();
		organisation.setOrganisationName(customerApplication.getBusinessName());
		organisation.setOrganisationType(2); //TODO this needs to be part of configuration and not hard-coded.
		organisation.setContactId(customerApplication.getDelegateContact());
		organisation.setAddressId(customerApplication.getBusinessAddress());
		organisation.getExtra().put(TRADINGNAME, customerApplication.getTradingName());
		organisation.getExtra().put(ABN, customerApplication.getABN());
	    // TODO : Put a list of owners CRNS (String[]) into hstore with OWNERLISTKEY=String[]
		// This is complicated. We may need to convert from HSTORE to JSONB datatype.
	    //hstore.put(OWNERLISTKEY, customerApplication.getOwnerList().toString()); // Convert JSON to String
	    organisation = organisationRepository.save(organisation);
	
		
        //Administrator = User(with EOI info)
	    // Only add hstore info if not sameAsDelegate as delegate already has the info.
		if ( customerApplication.isSameAsDelegate() )  {
		   customerApplication.getAdministratorUser().setContactId(customerApplication.getDelegateContact());	
		} else {
			customerApplication.getAdministratorUser().getContactId().getExtra().put(LICENCE, customerApplication.getAdministratorLicenceNo());
			customerApplication.getAdministratorUser().getContactId().getExtra().put(DATEOFBIRTH, customerApplication.getAdministratorDateOfBirth().toString());
		}
        customerApplication.getAdministratorUser().setContactId(contactRepository.save(customerApplication.getAdministratorUser().getContactId()));
		customerApplication.getAdministratorUser().setOrganisationId(organisation);
		customerApplication.setAdministratorUser(userRepository.save(customerApplication.getAdministratorUser()));

		// Stored in the application and applicationCustomer tables 
		// which have a one to one relationship.
		
		// Save the Application record to be used on Search Queue.
	    // Application - Holds generic application info about applications
		// and used for simple searches in queue 
		Application application = new Application();
	    application.setApplicationType(2); //TODO use configuration instead
	    application.setApplicationDescription(customerApplication.getBusinessName());
	    application.setApplicantId(customerApplication.getAdministratorUser());
	    application.setStatusId(1); // TODO set to 'Submitted' value from configuration
        //TODO Should this be in GMT/UTC ?
        application.setCreationDate(Calendar.getInstance());
        application.setCreatedBy(application.getApplicantId().getUserId());
        applicationRepository.save(application);

		// applicationCustomer - Holds info specific to customer applications only
        // and used for Advanced searches in queue.
	    ApplicationCustomer applicationCustomer = new ApplicationCustomer();
	    applicationCustomer.setApplicationId(application.getApplicationId());
	    applicationCustomer.setBusinessContactId(customerApplication.getBusinessContact());
	    applicationCustomer.setBusinessAddressId(customerApplication.getBusinessAddress());
	    applicationCustomer.setPostalAddressId(customerApplication.getPostalAddress());
	    applicationCustomer.setDelegateId(customerApplication.getDelegateContact());
	    applicationCustomer.setAdministratorId(customerApplication.getAdministratorUser());
	
		return customerApplication;
	}

	@Override
	public CustomerApplication update(CustomerApplication customer) {
		// TODO Auto-generated method stub
	    // update the customer as per the create.
	
		return null;
	}

	@Override
	public Void delete(CustomerApplication customer) {
		// TODO Auto-generated method stub
		// Do not delete the application, simply mark it as Withdrawn.
		// The applications cannot be with drawn unless the status is submitted.
		return null;
	}	

	private CustomerApplication validate(CustomerApplication customerApplication) {
		Error error = new Error();
	    List<Error> errors = new Stack<Error>();
	    // Validate each component
	    // there may be places we stop as cannot progress.

	    //
	    error.setSeverity(1);
	    error.setErrorCode(100);
	    errors.add(error);
	    
	    // Is ABN valid etc ?
	    // 
	    
	    // Does username already exist ?
	    // Is user email address already in user
	    // Is business/postal address already in use. 
	    // Is proprietor/delegate already in use.

	    // empty result for now.
	    customerApplication.setErrors(errors);
	    return customerApplication;
}
	
	private CustomerApplication populateEntity (ApplicationCustomer applicationCustomer) {
       
		CustomerApplication customerApplication = new CustomerApplication();

		// Core details
		customerApplication.setApplication(applicationCustomer.getApplication());
		customerApplication.setApplicationCustomer(applicationCustomer);
		customerApplication.setBusinessAddress(applicationCustomer.getBusinessAddressId());
		customerApplication.setPostalAddress(applicationCustomer.getPostalAddressId());

		// Now the customer specific.
		customerApplication.setTradingName(customerApplication.getApplication().getExtra().get("TRADINGNAME").toString());
		customerApplication.setABN(applicationCustomer.getApplication().getExtra().get("ABN").toString());
		
		return customerApplication;
		
	}


}
