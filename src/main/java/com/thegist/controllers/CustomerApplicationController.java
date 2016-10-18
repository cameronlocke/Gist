package com.thegist.controllers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.thegist.model.CustomerApplication;
import com.thegist.services.CustomerService;


@RestController
public class CustomerApplicationController {
	
//	@Autowired
	CustomerApplication CustomerApplication;  // Customer Application Object
	     
//	@Autowired
	CustomerService CustomerService;  // Customer Application Business logic
	    
	     
	//-------------------Retrieve Single Customer --------------------------------------------------------
	      
	@RequestMapping(value = "/customerapplication", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CustomerApplication>> getAllCustomers() {

		//TODO Do logging with AOP
		System.out.println("Fetching All  CustomerApplications");
		List<CustomerApplication> customerApplications = CustomerService.findAll();
	    if (customerApplications == null) {
	    	System.out.println("No customer applications available");
	    	return new ResponseEntity<List<CustomerApplication>>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<List<CustomerApplication>>(customerApplications, HttpStatus.OK);
	}
	  
	@RequestMapping(value = "/customerapplication/{Id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerApplication> getCustomer(@PathVariable("Id") int Id) {

		//TODO Do logging with AOP
		System.out.println("Fetching Customer with id " + Id);
		CustomerApplication customerApplication = CustomerService.findOne(Id);
	    if (customerApplication == null) {
	    	System.out.println("Customer with id " + Id + " not found");
	    	return new ResponseEntity<CustomerApplication>(HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<CustomerApplication>(CustomerApplication, HttpStatus.OK);
	}
	  
	    //-------------------Create a Customer--------------------------------------------------------
	    
	    @RequestMapping(value = "/customerapplication/", method = RequestMethod.POST)
	    public ResponseEntity<Void> createCustomer(@RequestBody CustomerApplication customerApplication, UriComponentsBuilder ucBuilder) {
	    	
	    	System.out.println("Creating Customer " + customerApplication.getBusinessName());
	    	CustomerService.create(customerApplication);
	  
	        HttpHeaders headers = new HttpHeaders();
	        headers.setLocation(ucBuilder.path("/Customer/{id}").buildAndExpand(customerApplication.getApplicationCustomer().toString()).toUri());
	        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	    }
	     
	      
	    //------------------- Update a Customer --------------------------------------------------------
	    @RequestMapping(value = "/customerapplication/{id}", method = RequestMethod.PUT)
	    public ResponseEntity<CustomerApplication> updateCustomer(@PathVariable("id") int id, @RequestBody CustomerApplication customerApplication) {

	    	System.out.println("Updating Customer " + id);
	        CustomerService.update(customerApplication);
	        return new ResponseEntity<CustomerApplication>(customerApplication, HttpStatus.OK);
	    }

	    
	    //------------------- Delete a customer application --------------------------------------------------------
	      
	    // An application is never deleted.  IT can only be marked with a status of withdrawn.  Once withdrawn (or rejected)
	    // the original information may be reused in another application.
	    
        // In rare circumstances an operator may ring the PMO advising that they do not wish to proceed.  A PMO officer will then 
	    // "somehow" withdraw the application.  Could be done with an update but with a delete the business logic
	    // is kept separate.     
	    
	    @RequestMapping(value = "/customerapplication/{id}", method = RequestMethod.DELETE)
	    public ResponseEntity<CustomerApplication> deleteCustomer(@PathVariable("id") int id, @RequestBody CustomerApplication customerApplication) {
	        System.out.println("Deleting All Users");
	        CustomerService.delete(customerApplication);
	        return new ResponseEntity<CustomerApplication>(HttpStatus.NO_CONTENT);
	    }

	    
	    
	    //TODO Remove before first candidate release. 
	    /* RetrieveAll and DeleteAll do not apply for Applications.
	    // Code left in so I can reference should I need it as a guide on other pages.
	    
	    //-------------------Retrieve All Customers --------------------------------------------------------

	    @RequestMapping(value = "/Customer/", method = RequestMethod.GET)
	    public ResponseEntity<List<Customer>> listAllCustomers() {
	        List<Customer> Customers = CustomerService.findAll();
	        if(Customers.isEmpty()){
	            return new ResponseEntity<List<Customer>>(HttpStatus.NO_CONTENT);//ERROR or just no data message
	        }
	        return new ResponseEntity<List<Customer>>(Customers, HttpStatus.OK);
	    }
	  
	  
	    */
}
