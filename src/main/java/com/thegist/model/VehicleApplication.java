package com.thegist.model;

import org.springframework.beans.factory.annotation.Autowired;

import com.thegist.domain.Application;
import com.thegist.domain.Vehicle;

public class VehicleApplication {

	@Autowired
    Application application;


	@Autowired
	Vehicle vehicle;
	
}
