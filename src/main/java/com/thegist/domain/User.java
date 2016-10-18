package com.thegist.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "user")
public class User {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Integer userId;

	public Integer getUserId() {
        return this.userId;
    }

	public void setUserId(Integer id) {
        this.userId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("roles", "applications", "applicationCustomers", "reviews", "contactId", "organisationId").toString();
    }

	@ManyToMany(mappedBy = "users")
    private Set<Role> roles;

	@OneToMany(mappedBy = "applicantId")
    private Set<Application> applications;

	@OneToMany(mappedBy = "administratorId")
    private Set<ApplicationCustomer> applicationCustomers;

	@OneToMany(mappedBy = "actionedBy")
    private Set<Review> reviews;

	@ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "contact_id")
    private Contact contactId;

	@ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisation_id")
    private Organisation organisationId;

	@Column(name = "user_name", length = 128)
    @NotNull
    private String userName;

	@Column(name = "password_salt", length = 10)
    private String passwordSalt;

	@Column(name = "password", length = 32)
    private String password;

	@Column(name = "locked")
    private Boolean locked;

	public Set<Role> getRoles() {
        return roles;
    }

	public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

	public Set<Application> getApplications() {
        return applications;
    }

	public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

	public Set<ApplicationCustomer> getApplicationCustomers() {
        return applicationCustomers;
    }

	public void setApplicationCustomers(Set<ApplicationCustomer> applicationCustomers) {
        this.applicationCustomers = applicationCustomers;
    }

	public Set<Review> getReviews() {
        return reviews;
    }

	public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

	public Contact getContactId() {
        return contactId;
    }

	public void setContactId(Contact contactId) {
        this.contactId = contactId;
    }

	public Organisation getOrganisationId() {
        return organisationId;
    }

	public void setOrganisationId(Organisation organisationId) {
        this.organisationId = organisationId;
    }

	public String getUserName() {
        return userName;
    }

	public void setUserName(String userName) {
        this.userName = userName;
    }

	public String getPasswordSalt() {
        return passwordSalt;
    }

	public void setPasswordSalt(String passwordSalt) {
        this.passwordSalt = passwordSalt;
    }

	public String getPassword() {
        return password;
    }

	public void setPassword(String password) {
        this.password = password;
    }

	public Boolean getLocked() {
        return locked;
    }

	public void setLocked(Boolean locked) {
        this.locked = locked;
    }
}
