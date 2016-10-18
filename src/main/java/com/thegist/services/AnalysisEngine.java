package com.thegist.services;

/* This is a major module that contain most of our IP
 * It needs LOST AND LOTS MORE WORK, and excessive amounts of testing.
 * 
 * The Concept
 * A Heavy Vehicle operator asks to travel of specific route OR within a designated area 
 * using the vehicle combination specified.  A number of this can occur during travel.
 * 1. 	Dimensional restrictions (height, width, length) will stop the vehicle physically passing.  
 * 		These are ERRORs and as such will require "manual" approval.  Although it is possible to submit
 *      the application it would only be in very, very rare circumstances that a travel would be issued.
 *		N.B. in the vary rare instance a permit is issued after a failure the description of the failure will 
 *		not be put on the permit (as the authority has excluded the restriction).
 *
 * 		TECH :  These should be managed using the Restrictions Manager (attribute type restriction) 
 * 		
 * 
 * 2.   Load Restrictions (force on structures).  Determines if a structure (bridge , culvert) is capable
 * 		of supporting the load within a healthy margin of safety. Note, it's not about catastrophic failure
 * 		of the asset but the effect on the life-span of the asset.  Like, dimension restrictions, a failure
 * 		is an ERROR and as such will require "manual" approval. Although it is possible to submit
 *      the application it would only be in very, very rare circumstances that a travel would be issued.
 *      
 *      TECH : The base/core simply performs simple beam analysis.  However, most states (depending on their
 *      engineers capabilities and data) may have their own methodology.  e.g. TMR has a concept of an 
 *      Equivalence Rating with compared load effects against a "like" vehicle.  
 *      
 * 3.	Temporary Restrictions.  In some instances a "temporary restriction" may stop travel (such as a road
 * 		closure for maintenance or flooding).  These are WARNINGS to the operator that you are permitted to 
 * 		travel BUT travel may be impeded. A permit can be system issued as long as the operator is aware of 
 * 		the restriction(s).  These will be put onto the permit.  
 * 		N.B. some temporary restrictions may have an indefinite life-cycle.
 * 
 * 		TECH :  These should be managed using the Restrictions Manager (ROAD CLOSURE type restriction) 
 * 
 * 4.	Conditions of Travel.  These are general informational conditions that must be followed for permitted
 * 		travel. Failure to comply with any travel condition invalidates the permit.     
 *     
 *     
 *     NOTE : Most of the assets won't have attributes applicable to each analysis type, we don't care we just look at all assets 
 *     and determine if the have the attribute. 
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thegist.domain.AreaOfOperation;
import com.thegist.domain.Restriction;
import com.thegist.domain.RestrictionRepository;
import com.thegist.domain.Route;
import com.thegist.domain.VehicleCombination;
import com.thegist.drn.domain.Asset;
import com.thegist.drn.domain.AssetRepository;
import com.thegist.model.AnalysisResult;
import com.thegist.model.Moment;
import com.vividsolutions.jts.geom.Geometry;

// Responsible for :
// - Route Analysis (restriction based) - Compare vehicle attributes against restrictions.
// - Area Analysis (perform route analysis on all routes in area)
// - Load Analysis (engineering force analysis) - perform for all bridges on route or in area.

// I don't think we want to expose this as should only used by other services.

public class AnalysisEngine {

	// TODO - We need to have a better way to get keys for attributes...
	// We don't want to "hard-code" hstore keys in the code...but we may have to.
	
	/*
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"1","value"=>"Road"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"2","value"=>"Bridge"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"3","value"=>"Culvert"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"4","value"=>"Tunnel"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"5","value"=>"Rail-Crossing"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"6","value"=>"Traffic Management Sign"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"7","value"=>"Power infrastructure"');
	INSERT INTO permit.lookup (lookup) VALUES ('"collection"=>"AssetType","key"=>"8","value"=>"Facility"');
	 */

	// Asset attributes - DimensionAnalysis
	final String MAX_HEIGHT = "MaxHeight";
	final String MAX_WIDTH = "MasxWidth";
	final String MAX_LENGTH = "MaxLength";

	// Asset attributes - LoadAnalysis
	final String MAX_BENDING_MOMENT = "MaxBendingMoment";
	final String MAX_PIER_REACTION = "MaxPierReaction";
	final String MAX_SHEAR_FORCE = "MaxShearForce";
	
	RestrictionRepository restrictionRepository;
	AssetRepository assetRepository;
	
	private List<AnalysisResult> dimensionAnalysis (List<Asset> assets, VehicleCombination vehicleCombination) {
	
		List<AnalysisResult> result = new ArrayList<AnalysisResult>();
		
		// Vehicle Combinations dimensions
		
		Double height = vehicleCombination.getDimensionId().getHeight().doubleValue();
		Double width = vehicleCombination.getDimensionId().getWidth().doubleValue();
		Double length = vehicleCombination.getDimensionId().getLength().doubleValue();
	
		for (Asset asset : assets) {
		   Map<String,String> assetAttributes = new HashMap<String, String>();
		   assetAttributes = asset.getAttributes();
		   for (Map.Entry<String, String> entry : assetAttributes.entrySet()) {
			   String key = entry.getKey();
			   Integer value = Integer.parseInt(entry.getValue());
			   switch (key) {
			   		case MAX_HEIGHT : if (height > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Height exceeded")); 
			   		case MAX_WIDTH : if (width > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Width exceeded"));
			   		case MAX_LENGTH : if (length > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Length exceeded"));
			   } //switch
		   }// for
		} //for
		return result;
	}
	
	private List<AnalysisResult> massAnalysis (List<Asset> assets, VehicleCombination vehicleCombination) {
		
		List<AnalysisResult> result = new ArrayList<AnalysisResult>();
		
		Map<String,String> assetAttributes = new HashMap<String, String>();
		
		for (Asset asset : assets) {
			Moment moment = new Moment(vehicleCombination.getAxleConfigurationId(), asset);
			assetAttributes = asset.getAttributes();
			for (Map.Entry<String, String> entry : assetAttributes.entrySet()) {
			   String key = entry.getKey();
			   Integer value = Integer.parseInt(entry.getValue());
			   switch (key) {
			   		case MAX_BENDING_MOMENT : if (moment.getBendingMoment() > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Bending moment exceeded")); 
			   		case MAX_PIER_REACTION : if (moment.getPierReaction() > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Pier Reaction exceeded"));
			   		case MAX_SHEAR_FORCE : if (moment.getShearForce() > value) result.add(new AnalysisResult(AnalysisResult.ERROR, "Maximum Shear Force exceeded"));
			   } //switch
		   } // for
		}
		return result;
	}
	
	private List<AnalysisResult> restrictionAnalysis(List<Restriction> restrictions) {
		return null;
	}
	
	private List<AnalysisResult> informationalAnalysis(List<Restriction> restrictions) {
		return null;
	}
		
	private List<AnalysisResult> analysis(Geometry routeGeom, VehicleCombination vehicleCombination) {
	
		List<AnalysisResult> result = new ArrayList<AnalysisResult>();
		
		// ERRORS (Red) - Cannot be system issued.
		List<Asset> assets = new ArrayList<Asset>();
		assets = assetRepository.findByGeometry(routeGeom);
		
		result = dimensionAnalysis(assets, vehicleCombination); // Compare height, width, length against asset attributes (blank means not required).
		result.addAll(massAnalysis(assets, vehicleCombination));  // performed for asset types of bridge and culvert ONLY. (minERb, maxERb stored in attributes)
		
		// Temporary Restrictions
		// WARNINGS (Amber) - Can be system issued with applicants acceptance.
		// Closures and Do NOT cross structures - the permit can be issued BUT with explicit operating restrictions that they cannot
		// pass. User may get permit but with explicit travel condition.
		List<Restriction> restrictions = new ArrayList<Restriction>();
		result.addAll(restrictionAnalysis(restrictions));  // temporary restrictions (closures)
		
		// INFORMATIONAL (Green) - Can be System issued.
		result.addAll(informationalAnalysis(restrictions)); // travel conditions. 
	
		return result;
	}

	public List<AnalysisResult> routeAnalysis (Route route, VehicleCombination vehicleCombination) {
		// traverse route (by every segment)
		// check height, width, length against on each segment
		// check pavement ??? General Condition
		List<AnalysisResult> result = new ArrayList<AnalysisResult>(); 
		result = analysis(route.getTheGeom(), vehicleCombination);
		return result;
		
	}

	public List<AnalysisResult> areaAnalysis (AreaOfOperation areaOfOperation, VehicleCombination vehicleCombination) {
		
		// Use GIS functions to determine restrictions on route
		// compare restriction against vehicle
		// Find informational restrictions.
		List<AnalysisResult> result = new ArrayList<AnalysisResult>(); 
		result = analysis(areaOfOperation.getTheGeom(), vehicleCombination);
		return result;
		
	}



}


