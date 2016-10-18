package com.thegist.domain;
import java.util.Calendar;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(schema = "overload",name = "permit")
public class Permit {

	@OneToMany(mappedBy = "permitId")
    private Set<Application> applications;

	@OneToMany(mappedBy = "permitId")
    private Set<TravelConditions> travelConditionss;

	@Column(name = "permit_number", length = 15)
    private String permitNumber;

	@Column(name = "effective_from")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar effectiveFrom;

	@Column(name = "effective_to")
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "MM")
    private Calendar effectiveTo;


	@Column(name = "document_link", length = 255)
    private String documentLink;

	public Set<Application> getApplications() {
        return applications;
    }

	public void setApplications(Set<Application> applications) {
        this.applications = applications;
    }

	public Set<TravelConditions> getTravelConditionss() {
        return travelConditionss;
    }

	public void setTravelConditionss(Set<TravelConditions> travelConditionss) {
        this.travelConditionss = travelConditionss;
    }

	public String getPermitNumber() {
        return permitNumber;
    }

	public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

	public Calendar getEffectiveFrom() {
        return effectiveFrom;
    }

	public void setEffectiveFrom(Calendar effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

	public Calendar getEffectiveTo() {
        return effectiveTo;
    }

	public void setEffectiveTo(Calendar effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

	public String getDocumentLink() {
        return documentLink;
    }

	public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "permit_id")
    private Integer permitId;

	public Integer getPermitId() {
        return this.permitId;
    }

	public void setPermitId(Integer id) {
        this.permitId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applications", "travelConditionss").toString();
    }
}
