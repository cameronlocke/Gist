package com.thegist.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "application_customer")
public class ApplicationCustomer {

	@OneToOne
    @JoinColumn(name = "application_id", nullable = false, insertable = false, updatable = false)
    private Application application;

	@ManyToOne
    @JoinColumn(name = "postal_address_id", referencedColumnName = "address_id")
    private Address postalAddressId;

	@ManyToOne
    @JoinColumn(name = "business_address_id", referencedColumnName = "address_id")
    private Address businessAddressId;

	@ManyToOne
    @JoinColumn(name = "delegate_id", referencedColumnName = "contact_id")
    private Contact delegateId;

	@ManyToOne
    @JoinColumn(name = "business_contact_id", referencedColumnName = "contact_id")
    private Contact businessContactId;

	@ManyToOne
    @JoinColumn(name = "administrator_id", referencedColumnName = "user_id")
    private User administratorId;

	public Application getApplication() {
        return application;
    }

	public void setApplication(Application application) {
        this.application = application;
    }

	public Address getPostalAddressId() {
        return postalAddressId;
    }

	public void setPostalAddressId(Address postalAddressId) {
        this.postalAddressId = postalAddressId;
    }

	public Address getBusinessAddressId() {
        return businessAddressId;
    }

	public void setBusinessAddressId(Address businessAddressId) {
        this.businessAddressId = businessAddressId;
    }

	public Contact getDelegateId() {
        return delegateId;
    }

	public void setDelegateId(Contact delegateId) {
        this.delegateId = delegateId;
    }

	public Contact getBusinessContactId() {
        return businessContactId;
    }

	public void setBusinessContactId(Contact businessContactId) {
        this.businessContactId = businessContactId;
    }

	public User getAdministratorId() {
        return administratorId;
    }

	public void setAdministratorId(User administratorId) {
        this.administratorId = administratorId;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Integer applicationId;

	public Integer getApplicationId() {
        return this.applicationId;
    }

	public void setApplicationId(Integer id) {
        this.applicationId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("application", "postalAddressId", "businessAddressId", "delegateId", "businessContactId", "administratorId").toString();
    }
}
