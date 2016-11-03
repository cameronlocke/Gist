package com.thegist.model;

import java.util.Date;
import java.util.List;

// A 'super' class used to combine all elements of a Customer application into 
// one model (i.e. Trading Name, Business ADdress, Postal Address etc  
// This is the object that is passed around between UI and the controller.

import org.springframework.beans.factory.annotation.Autowired;

import com.thegist.domain.Address;
import com.thegist.domain.Application;
import com.thegist.domain.ApplicationCustomer;
import com.thegist.domain.Contact;
import com.thegist.domain.User;
import com.thegist.utils.messages.Error;


public class CustomerApplication {
	
	@Autowired
    Application application;

	@Autowired
	ApplicationCustomer applicationCustomer;
	
	String businessName;
	
	String tradingName;

	String abn;

	@Autowired
    Contact businessContact;
	
    String[] vehicleOwners; 

    boolean sameAsBusinessContact;
    
	@Autowired
    Contact delegateContact;

	Date delegateDateOfBirth;
	
	String delegateLicenceNo;

    boolean sameAsDelegate;
    
	@Autowired
    User administratorUser;

	Date administratorDateOfBirth;
	
	String administratorLicenceNo;

	@Autowired
    User modifiedBy; 

    @Autowired
    Address postalAddress;
    
    @Autowired
    Address businessAddress;

	List<Error> errors;
    
    
	public ApplicationCustomer getApplicationCustomer() {
		return applicationCustomer;
	}

	public void setApplicationCustomer(ApplicationCustomer applicationCustomer) {
		this.applicationCustomer = applicationCustomer;
	}

	public String getBusinessName() {
		return businessName;
	}

	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public String getTradingName() {
		return tradingName;
	}

	public void setTradingName(String tradingName) {
		this.tradingName = tradingName;
	}

	public String getABN() {
		return abn;
	}

	public void setABN(String abn) {
		this.abn = abn;
	}

	public Contact getBusinessContact() {
		return businessContact;
	}

	public void setBusinessContact(Contact businessContact) {
		this.businessContact = businessContact;
	}

	public String[] getVehicleOwners() {
		return vehicleOwners;
	}

	public void setVehicleOwners(String[] vehicleOwners) {
		this.vehicleOwners = vehicleOwners;
	}

	public boolean isSameAsBusinessContact() {
		return sameAsBusinessContact;
	}

	public void setSameAsBusinessContact(boolean sameAsBusinessContact) {
		this.sameAsBusinessContact = sameAsBusinessContact;
	}

	public Contact getDelegateContact() {
		return delegateContact;
	}

	public void setDelegateContact(Contact delegateContact) {
		this.delegateContact = delegateContact;
	}

	public Date getDelegateDateOfBirth() {
		return delegateDateOfBirth;
	}

	public void setDelegateDateOfBirth(Date delegateDateOfBirth) {
		this.delegateDateOfBirth = delegateDateOfBirth;
	}

	public String getDelegateLicenceNo() {
		return delegateLicenceNo;
	}

	public void setDelegateLicenceNo(String delegateLicenceNo) {
		this.delegateLicenceNo = delegateLicenceNo;
	}

	public boolean isSameAsDelegate() {
		return sameAsDelegate;
	}

	public void setSameAsDelegate(boolean sameAsDelegate) {
		this.sameAsDelegate = sameAsDelegate;
	}

	public User getAdministratorUser() {
		return administratorUser;
	}

	public void setAdministratorUser(User administratorUser) {
		this.administratorUser = administratorUser;
	}

	public Date getAdministratorDateOfBirth() {
		return administratorDateOfBirth;
	}

	public void setAdministratorDateOfBirth(Date administratorDateOfBirth) {
		this.administratorDateOfBirth = administratorDateOfBirth;
	}

	public String getAdministratorLicenceNo() {
		return administratorLicenceNo;
	}

	public void setAdministratorLicenceNo(String administratorLicenceNo) {
		this.administratorLicenceNo = administratorLicenceNo;
	}

	public User getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(User modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Address getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(Address postalAddress) {
		this.postalAddress = postalAddress;
	}

	public Address getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(Address businessAddress) {
		this.businessAddress = businessAddress;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getAbn() {
		return abn;
	}

	public void setAbn(String abn) {
		this.abn = abn;
	}

	public List<Error> getErrors() {
		return errors;
	}

	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
    

}
