package com.thegist.model;

import com.thegist.domain.Axle;
import com.thegist.domain.AxleConfiguration;
import com.thegist.drn.domain.Asset;

// Class to hold various engineering beam calculations

public class Moment {
	
	private double bendingMoment;
	private double pierReaction;   // RHS by convention
	private double shearForce;
	
	final private String NO_OF_SPANS = "NumSpans"; 
	final private String SPAN_LENGTH = "SpanLength#"; 
	final private double GRAVITY = 9.8; // 9.78033 =  normal equatorial value on Earth

	public double getBendingMoment() {
		return bendingMoment;
	}

	public void setBendingMoment(double bendingMoment) {
		this.bendingMoment = bendingMoment;
	}
	
	public double getPierReaction() {
		return pierReaction;
	}
	
	public void setPierReaction(double pierReaction) {
		this.pierReaction = pierReaction;
	}
	
	public double getShearForce() {
		return shearForce;
	}
	
	public void setShearForce(double shearForce) {
		this.shearForce = shearForce;
	}
	
	private double calcBendingMoment(AxleConfiguration axleConfiguration, double x) {
		// NOTE : This for simply supported spans.
		// Continuous bridges calculations are different
		// go from first axle to x.
		// NOTE : 
		//   Sometimes the vehicle is run in a mirror. ie. rear axle first in conventional direction.
		//  supposedly this may cause a different result.
		double result = 0;
		for (Axle axle : axleConfiguration.getAxles()) {
			//TODO fix this.  Calc forces on span.
			if (axle.getGroundContactLength().doubleValue() < x) { 
			   result = result + (x * axle.getMass().doubleValue() * GRAVITY);
			} //if
		} //for
		return result;
	}
	
	private double calcPierReaction(double load, double x, double length) {
		return (load * (x / length));
	}
	
	private double calcShearForce (double load, double reaction) {
		return (load / reaction);
	}
	
	public Moment (AxleConfiguration axleConfiguration, Asset asset) {
		super();
		Integer noOfSpans = new Integer(asset.getAttributes().get(NO_OF_SPANS).toString());
		for (Integer spanNo = 1; spanNo < noOfSpans; ++spanNo) {
			double spanStart = 0;
			double spanLength = Double.parseDouble(asset.getAttributes().get(SPAN_LENGTH+"spanNo"));
			double spanStep = 0.015; // 15mm to 0.015m // taken from properties file
			for(double x=spanStart; x < spanLength; x+=spanStep) {
			    double bm = calcBendingMoment(axleConfiguration, x);
				if (bm > bendingMoment)  {
					bendingMoment = bm;
				}
				double pr = pierReaction = calcPierReaction(bendingMoment, x, spanLength);
				if (pr > pierReaction)  {
					pierReaction = pr;
				}
			    double sf = calcShearForce(bendingMoment, pierReaction);
				if (sf > shearForce)  {
					shearForce = sf;
				}
		    } // for
		} // for
	}
}
