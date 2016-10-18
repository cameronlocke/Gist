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

import com.thegist.utils.HstoreUserType;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(schema = "overload",name = "area_of_operation")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class AreaOfOperation {

	@OneToMany(mappedBy = "areaOfOperationId")
    private Set<ApplicationArea> applicationAreas;

	@Column(name = "name")
    private String name;

	@Column(name = "descritpion")
    private String descritpion;

	@Column(name = "the_geom", columnDefinition = "geometry")
    private Geometry theGeom;

	public Set<ApplicationArea> getApplicationAreas() {
        return applicationAreas;
    }

	public void setApplicationAreas(Set<ApplicationArea> applicationAreas) {
        this.applicationAreas = applicationAreas;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getDescritpion() {
        return descritpion;
    }

	public void setDescritpion(String descritpion) {
        this.descritpion = descritpion;
    }

	public Geometry getTheGeom() {
        return theGeom;
    }

	public void setTheGeom(Geometry theGeom ) {
        this.theGeom = theGeom;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "area_of_operation_id")
    private Integer areaOfOperationId;

	public Integer getAreaOfOperationId() {
        return this.areaOfOperationId;
    }

	public void setAreaOfOperationId(Integer id) {
        this.areaOfOperationId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationAreas").toString();
    }
}
