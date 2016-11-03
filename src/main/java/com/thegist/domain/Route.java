package com.thegist.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.TypeDef;

import com.thegist.utils.hstore.HstoreUserType;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(schema = "overload",name = "route")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Route {

	@OneToMany(mappedBy = "routeId")
    private Set<ApplicationRoadManagerConsent> applicationRoadManagerConsents;

	@OneToMany(mappedBy = "routeId")
    private Set<ApplicationRoute> applicationRoutes;

	@OneToMany(mappedBy = "routeId")
    private Set<RouteAddress> routeAddresses;

	@Column(name = "the_geom", columnDefinition = "geometry")
    private Geometry theGeom;

	@Column(name = "directions", length = 255)
    private String directions;

	public Set<ApplicationRoadManagerConsent> getApplicationRoadManagerConsents() {
        return applicationRoadManagerConsents;
    }

	public void setApplicationRoadManagerConsents(Set<ApplicationRoadManagerConsent> applicationRoadManagerConsents) {
        this.applicationRoadManagerConsents = applicationRoadManagerConsents;
    }

	public Set<ApplicationRoute> getApplicationRoutes() {
        return applicationRoutes;
    }

	public void setApplicationRoutes(Set<ApplicationRoute> applicationRoutes) {
        this.applicationRoutes = applicationRoutes;
    }

	public Set<RouteAddress> getRouteAddresses() {
        return routeAddresses;
    }

	public void setRouteAddresses(Set<RouteAddress> routeAddresses) {
        this.routeAddresses = routeAddresses;
    }

	public Geometry getTheGeom() {
        return theGeom;
    }

	public void setTheGeom(Geometry theGeom ) {
        this.theGeom = theGeom;
    }
	public String getDirections() {
        return directions;
    }

	public void setDirections(String directions) {
        this.directions = directions;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_id")
    private Integer routeId;

	public Integer getRouteId() {
        return this.routeId;
    }

	public void setRouteId(Integer id) {
        this.routeId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationRoadManagerConsents", "applicationRoutes", "routeAddresses").toString();
    }
}
