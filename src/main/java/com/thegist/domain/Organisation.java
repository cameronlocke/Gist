package com.thegist.domain;
import java.util.HashMap;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.TypeDef;

import com.thegist.utils.hstore.HstoreUserType;


@Entity
@Table(schema = "overload",name = "organisation")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Organisation {

	@OneToMany(mappedBy = "organisationId")
    private Set<Application> applications;

	@OneToMany(mappedBy = "assignedTo")
    private Set<Review> reviews;

	@OneToMany(mappedBy = "organisationId")
    private Set<SavedRoute> savedRoutes;

	@OneToMany(mappedBy = "organisationId")
    private Set<User> users;

	@OneToMany(mappedBy = "organisationId")
    private Set<Vehicle> vehicles;

	@ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address addressId;

	@ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
    private Contact contactId;

	@Column(name = "organisation_name", length = 10)
    @NotNull
    private String organisationName;

	@Column(name = "organisation_type")
    @NotNull
    private Integer organisationType;

	@Column(name = "extra", columnDefinition = "hstore")
	private HashMap<String, String> extra = new HashMap<String, String>();

	public Set<Application> getApplications() {
        return applications;
    }

	public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

	public Set<Review> getReviews() {
        return reviews;
    }

	public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

	public Set<SavedRoute> getSavedRoutes() {
        return savedRoutes;
    }

	public void setSavedRoutes(Set<SavedRoute> savedRoutes) {
        this.savedRoutes = savedRoutes;
    }

	public Set<User> getUsers() {
        return users;
    }

	public void setUsers(Set<User> users) {
        this.users = users;
    }

	public Set<Vehicle> getVehicles() {
        return vehicles;
    }

	public void setVehicles(Set<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

	public Address getAddressId() {
        return addressId;
    }

	public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

	public Contact getContactId() {
        return contactId;
    }

	public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

	public String getOrganisationName() {
        return organisationName;
    }

	public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

	public Integer getOrganisationType() {
        return organisationType;
    }

	public void setOrganisationType(Integer organisationType) {
        this.organisationType = organisationType;
    }

	public HashMap<String, String> getExtra() {
        return extra;
    }

	public void setExtra(HashMap<String, String> extra) {
        this.extra = extra;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "organisation_id")
    private Integer organisationId;

	public Integer getOrganisationId() {
        return this.organisationId;
    }

	public void setOrganisationId(Integer id) {
        this.organisationId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applications", "reviews", "savedRoutes", "users", "vehicles", "addressId", "contactId").toString();
    }
}
