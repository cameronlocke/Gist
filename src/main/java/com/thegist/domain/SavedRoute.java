package com.thegist.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "saved_route")
public class SavedRoute {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("organisationId").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "saved_route_id")
    private Integer savedRouteId;

	public Integer getSavedRouteId() {
        return this.savedRouteId;
    }

	public void setSavedRouteId(Integer id) {
        this.savedRouteId = id;
    }

	@ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisation_id")
    private Organisation organisationId;

	@Column(name = "shortname", length = 20)
    private String shortname;

	@Column(name = "description", length = 100)
    private String description;

	@Column(name = "route_id")
    private Integer routeId;

	public Organisation getOrganisationId() {
        return organisationId;
    }

	public void setOrganisationId(Organisation organisationId) {
        this.organisationId = organisationId;
    }

	public String getShortname() {
        return shortname;
    }

	public void setShortname(String shortname) {
        this.shortname = shortname;
    }

	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }

	public Integer getRouteId() {
        return routeId;
    }

	public void setRouteId(Integer routeId) {
        this.routeId = routeId;
    }
}
