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
@Table(schema = "overload",name = "address")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Address {

	@OneToMany(mappedBy = "businessAddressId")
    private Set<ApplicationCustomer> applicationCustomers;

	@OneToMany(mappedBy = "postalAddressId")
    private Set<ApplicationCustomer> applicationCustomers1;

	@OneToMany(mappedBy = "addressId")
    private Set<Organisation> organisations;

	@OneToMany(mappedBy = "addressId")
    private Set<RouteAddress> routeAddresses;

	@Column(name = "address_line_1", length = 100)
    private String addressLine1;

	@Column(name = "address_line_2", length = 100)
    private String addressLine2;

	@Column(name = "town", length = 50)
    private String town;

	@Column(name = "state_id")
    private Integer stateId;

	@Column(name = "country_id")
    private Integer countryId;

	@Column(name = "postcode", length = 4)
    private String postcode;

	@Column(name = "the_geom", columnDefinition = "geometry")
    private Geometry theGeom;
	public Set<ApplicationCustomer> getApplicationCustomers() {
        return applicationCustomers;
    }

	public void setApplicationCustomers(Set<ApplicationCustomer> applicationCustomers) {
        this.applicationCustomers = applicationCustomers;
    }

	public Set<ApplicationCustomer> getApplicationCustomers1() {
        return applicationCustomers1;
    }

	public void setApplicationCustomers1(Set<ApplicationCustomer> applicationCustomers1) {
        this.applicationCustomers1 = applicationCustomers1;
    }

	public Set<Organisation> getOrganisations() {
        return organisations;
    }

	public void setOrganisations(Set<Organisation> organisations) {
        this.organisations = organisations;
    }

	public Set<RouteAddress> getRouteAddresses() {
        return routeAddresses;
    }

	public void setRouteAddresses(Set<RouteAddress> routeAddresses) {
        this.routeAddresses = routeAddresses;
    }

	public String getAddressLine1() {
        return addressLine1;
    }

	public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

	public String getAddressLine2() {
        return addressLine2;
    }

	public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

	public String getTown() {
        return town;
    }

	public void setTown(String town) {
        this.town = town;
    }

	public Integer getStateId() {
        return stateId;
    }

	public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

	public Integer getCountryId() {
        return countryId;
    }

	public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

	public String getPostcode() {
        return postcode;
    }

	public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

	public Geometry getTheGeom() {
        return theGeom;
    }

	public void setTheGeom(Geometry theGeom ) {
        this.theGeom = theGeom;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private Integer addressId;

	public Integer getAddressId() {
        return this.addressId;
    }

	public void setAddressId(Integer id) {
        this.addressId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationCustomers", "applicationCustomers1", "organisations", "routeAddresses").toString();
    }
}
