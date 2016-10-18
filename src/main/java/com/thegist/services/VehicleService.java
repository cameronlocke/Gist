package com.thegist.services;

import java.util.List;

import com.thegist.model.VehicleApplication;

public interface VehicleService {

	// Not exactly the same as the vehicle domain object but close.
	// This is the Vehicle application service where customer, vehicle and route info is provided.
	// resulting in a record in the application table for search purposes. 
	
	// Before a applications can be accepted the following needs to be validated.
	// also needs to display errors/warnings during issuance process.
	// VLM Vehicle validation - 
	// Under regulation (ERROR no permit required)
	// Under guideline (WARNING permit may not be required)
	// exceeds VLM (Warning permit may not be issued).

	public List<VehicleApplication> findAll(); 
	
	public List<VehicleApplication> findByApplicationId(Integer id);

	public VehicleApplication findOne(Integer Id);

	public VehicleApplication save(VehicleApplication vehicleApplication);
	
}
