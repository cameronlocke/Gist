package com.thegist.model;

import java.util.Calendar;

import com.thegist.domain.Route;
import com.thegist.domain.Vehicle;

public class RouteApplication {
	
	
	private Calendar effectiveFrom;
	
	private Route route;
	
	private Vehicle[] vehicles;

	public Calendar getEffectiveFrom() {
		return effectiveFrom;
	}

	public void setEffectiveFrom(Calendar effectiveFrom) {
		this.effectiveFrom = effectiveFrom;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public Vehicle[] getVehicles() {
		return vehicles;
	}

	public void setVehicles(Vehicle[] vehicles) {
		this.vehicles = vehicles;
	}
	
	
}
