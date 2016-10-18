package com.thegist.domain;
import java.util.HashMap;
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

import com.thegist.utils.HstoreUserType;

@Entity
@Table(schema = "overload",name = "contact")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Contact {

	@OneToMany(mappedBy = "businessContactId")
    private Set<ApplicationCustomer> applicationCustomers;

	@OneToMany(mappedBy = "delegateId")
    private Set<ApplicationCustomer> applicationCustomers1;

	@OneToMany(mappedBy = "contactId")
    private Set<Organisation> organisations;

	@OneToMany(mappedBy = "contactId")
    private Set<User> users;

	@Column(name = "firstname", length = 64)
    private String firstname;

	@Column(name = "middlename", length = 64)
    private String middlename;

	@Column(name = "surname", length = 64)
    private String surname;

	@Column(name = "extra", columnDefinition = "hstore")
	private HashMap<String, String> extra = new HashMap<String, String>();

	
	@Column(name = "email_address", length = 128)
    private String emailAddress;

	@Column(name = "phone")
    private String phone;

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

	public Set<User> getUsers() {
        return users;
    }

	public void setUsers(Set<User> users) {
        this.users = users;
    }

	public String getFirstname() {
        return firstname;
    }

	public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

	public String getMiddlename() {
        return middlename;
    }

	public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

	public String getSurname() {
        return surname;
    }

	public void setSurname(String surname) {
        this.surname = surname;
    }

	public HashMap<String, String> getExtra() {
        return extra;
    }

	public void setExtra(HashMap<String, String> extra) {
        this.extra = extra;
    }

	public String getEmailAddress() {
        return emailAddress;
    }

	public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

	public String getPhone() {
        return phone;
    }

	public void setPhone(String phone) {
        this.phone = phone;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationCustomers", "applicationCustomers1", "organisations", "users").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "contact_id")
    private Integer contactId;

	public Integer getContactId() {
        return this.contactId;
    }

	public void setContactId(Integer id) {
        this.contactId = id;
    }
}
