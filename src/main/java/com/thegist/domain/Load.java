package com.thegist.domain;
import java.math.BigDecimal;
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

@Entity
@Table(schema = "overload",name = "load")
public class Load {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "load_id")
    private Integer loadId;

	public Integer getLoadId() {
        return this.loadId;
    }

	public void setLoadId(Integer id) {
        this.loadId = id;
    }

	@OneToMany(mappedBy = "loadId")
    private Set<ApplicationArea> applicationAreas;

	@OneToMany(mappedBy = "loadId")
    private Set<ApplicationRoadManagerConsent> applicationRoadManagerConsents;

	@OneToMany(mappedBy = "loadId")
    private Set<ApplicationRoute> applicationRoutes;

	@Column(name = "load_mass", precision = 10, scale = 2)
    private BigDecimal loadMass;

	@Column(name = "load_type_id")
    private Integer loadTypeId;

	@Column(name = "load_description", length = 30)
    private String loadDescription;

	public Set<ApplicationArea> getApplicationAreas() {
        return applicationAreas;
    }

	public void setApplicationAreas(Set<ApplicationArea> applicationAreas) {
        this.applicationAreas = applicationAreas;
    }

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

	public BigDecimal getLoadMass() {
        return loadMass;
    }

	public void setLoadMass(BigDecimal loadMass) {
        this.loadMass = loadMass;
    }

	public Integer getLoadTypeId() {
        return loadTypeId;
    }

	public void setLoadTypeId(Integer loadTypeId) {
        this.loadTypeId = loadTypeId;
    }

	public String getLoadDescription() {
        return loadDescription;
    }

	public void setLoadDescription(String loadDescription) {
        this.loadDescription = loadDescription;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationAreas", "applicationRoadManagerConsents", "applicationRoutes").toString();
    }
}
