package com.thegist.domain;
import java.util.Calendar;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

import com.thegist.utils.HstoreUserType;

import org.hibernate.annotations.TypeDef;


@Entity
@Table(schema = "overload",name = "application")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Application {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationArea", "applicationCustomer", "applicationRoadManagerConsent", "applicationRoute", "applicationVehicle", "reviews", "organisationId", "permitId", "applicantId").toString();
    }

	@OneToOne(mappedBy = "application")
    private ApplicationArea applicationArea;

	@OneToOne(mappedBy = "application")
    private ApplicationCustomer applicationCustomer;

	@OneToOne(mappedBy = "application")
    private ApplicationRoadManagerConsent applicationRoadManagerConsent;

	@OneToOne(mappedBy = "application")
    private ApplicationRoute applicationRoute;

	@OneToOne(mappedBy = "application")
    private ApplicationVehicle applicationVehicle;

	@OneToMany(mappedBy = "applicationId")
    private Set<Review> reviews;

	@ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisation_id")
    private Organisation organisationId;

	@ManyToOne
    @JoinColumn(name = "permit_id", referencedColumnName = "permit_id")
    private Permit permitId;

	@ManyToOne
    @JoinColumn(name = "applicant_id", referencedColumnName = "user_id")
    private User applicantId;

	@Column(name = "application_type")
    private Integer applicationType;

	@Column(name = "application_description")
    private String applicationDescription;

	@Column(name = "coverage_from")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar coverageFrom;

	@Column(name = "coverage_to")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar coverageTo;

	@Column(name = "extra", columnDefinition = "hstore")
	private HashMap<String, String> extra = new HashMap<String, String>();
	
	@Column(name = "status_id")
    private Integer statusId;

	@Column(name = "locked")
    private Boolean locked;

	@Column(name = "locked_by")
    private Integer lockedBy;

	@Column(name = "creation_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar creationDate;

	@Column(name = "created_by")
    private Integer createdBy;

	@Column(name = "modification_date")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar modificationDate;

	@Column(name = "modified_by")
    private Integer modifiedBy;

	public ApplicationArea getApplicationArea() {
        return applicationArea;
    }

	public void setApplicationArea(ApplicationArea applicationArea) {
        this.applicationArea = applicationArea;
    }

	public ApplicationCustomer getApplicationCustomer() {
        return applicationCustomer;
    }

	public void setApplicationCustomer(ApplicationCustomer applicationCustomer) {
        this.applicationCustomer = applicationCustomer;
    }

	public ApplicationRoadManagerConsent getApplicationRoadManagerConsent() {
        return applicationRoadManagerConsent;
    }

	public void setApplicationRoadManagerConsent(ApplicationRoadManagerConsent applicationRoadManagerConsent) {
        this.applicationRoadManagerConsent = applicationRoadManagerConsent;
    }

	public ApplicationRoute getApplicationRoute() {
        return applicationRoute;
    }

	public void setApplicationRoute(ApplicationRoute applicationRoute) {
        this.applicationRoute = applicationRoute;
    }

	public ApplicationVehicle getApplicationVehicle() {
        return applicationVehicle;
    }

	public void setApplicationVehicle(ApplicationVehicle applicationVehicle) {
        this.applicationVehicle = applicationVehicle;
    }

	public Set<Review> getReviews() {
        return reviews;
    }

	public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }

	public Organisation getOrganisationId() {
        return organisationId;
    }

	public void setOrganisationId(Organisation organisationId) {
        this.organisationId = organisationId;
    }

	public Permit getPermitId() {
        return permitId;
    }

	public void setPermitId(Permit permitId) {
        this.permitId = permitId;
    }

	public User getApplicantId() {
        return applicantId;
    }

	public void setApplicantId(User applicantId) {
        this.applicantId = applicantId;
    }

	public Integer getApplicationType() {
        return applicationType;
    }

	public void setApplicationType(Integer applicationType) {
        this.applicationType = applicationType;
    }

	public String getApplicationDescription() {
        return applicationDescription;
    }

	public void setApplicationDescription(String applicationDescription) {
        this.applicationDescription = applicationDescription;
    }

	public Calendar getCoverageFrom() {
        return coverageFrom;
    }

	public void setCoverageFrom(Calendar coverageFrom) {
        this.coverageFrom = coverageFrom;
    }

	public Calendar getCoverageTo() {
        return coverageTo;
    }

	public void setCoverageTo(Calendar coverageTo) {
        this.coverageTo = coverageTo;
    }

	public HashMap<String, String> getExtra() {
        return extra;
    }

	public void setExtra(HashMap<String, String> extra) {
        this.extra = extra;
    }
	
	public Integer getStatusId() {
        return statusId;
    }

	public void setStatusId(Integer statusId) {
        this.statusId = statusId;
    }

	public Boolean getLocked() {
        return locked;
    }

	public void setLocked(Boolean locked) {
        this.locked = locked;
    }

	public Integer getLockedBy() {
        return lockedBy;
    }

	public void setLockedBy(Integer lockedBy) {
        this.lockedBy = lockedBy;
    }

	public Calendar getCreationDate() {
        return creationDate;
    }

	public void setCreationDate(Calendar creationDate) {
        this.creationDate = creationDate;
    }

	public Integer getCreatedBy() {
        return createdBy;
    }

	public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

	public Calendar getModificationDate() {
        return modificationDate;
    }

	public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

	public Integer getModifiedBy() {
        return modifiedBy;
    }

	public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
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
}
