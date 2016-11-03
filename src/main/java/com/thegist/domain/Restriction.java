package com.thegist.domain;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.hibernate.annotations.TypeDef;

import com.thegist.utils.hstore.HstoreUserType;
import com.vividsolutions.jts.geom.Geometry;

@Entity
@Table(schema = "overload",name = "restriction")
@TypeDef(name = "hstore", typeClass = HstoreUserType.class)
public class Restriction {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "restriction_id")
    private Integer restrictionId;

	public Integer getRestrictionId() {
        return this.restrictionId;
    }

	public void setRestrictionId(Integer id) {
        this.restrictionId = id;
    }

	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	@Column(name = "max_length", precision = 10, scale = 3)
    private BigDecimal maxLength;

	@Column(name = "max_width", precision = 10, scale = 3)
    private BigDecimal maxWidth;

	@Column(name = "max_height", precision = 10, scale = 3)
    private BigDecimal maxHeight;

	@Column(name = "opp_max_width", precision = 10, scale = 3)
    private BigDecimal oppMaxWidth;

	@Column(name = "opp_max_height", precision = 10, scale = 3)
    private BigDecimal oppMaxHeight;

	@Column(name = "max_bending_moment", precision = 10, scale = 3)
    private BigDecimal maxBendingMoment;

	@Column(name = "max_shear", precision = 10, scale = 3)
    private BigDecimal maxShear;

	@Column(name = "direction")
    private Character direction;

	@Column(name = "the_geom", columnDefinition = "geometry")
    private Geometry theGeom;


	public BigDecimal getMaxLength() {
        return maxLength;
    }

	public void setMaxLength(BigDecimal maxLength) {
        this.maxLength = maxLength;
    }

	public BigDecimal getMaxWidth() {
        return maxWidth;
    }

	public void setMaxWidth(BigDecimal maxWidth) {
        this.maxWidth = maxWidth;
    }

	public BigDecimal getMaxHeight() {
        return maxHeight;
    }

	public void setMaxHeight(BigDecimal maxHeight) {
        this.maxHeight = maxHeight;
    }

	public BigDecimal getOppMaxWidth() {
        return oppMaxWidth;
    }

	public void setOppMaxWidth(BigDecimal oppMaxWidth) {
        this.oppMaxWidth = oppMaxWidth;
    }

	public BigDecimal getOppMaxHeight() {
        return oppMaxHeight;
    }

	public void setOppMaxHeight(BigDecimal oppMaxHeight) {
        this.oppMaxHeight = oppMaxHeight;
    }

	public BigDecimal getMaxBendingMoment() {
        return maxBendingMoment;
    }

	public void setMaxBendingMoment(BigDecimal maxBendingMoment) {
        this.maxBendingMoment = maxBendingMoment;
    }

	public BigDecimal getMaxShear() {
        return maxShear;
    }

	public void setMaxShear(BigDecimal maxShear) {
        this.maxShear = maxShear;
    }

	public Character getDirection() {
        return direction;
    }

	public void setDirection(Character direction) {
        this.direction = direction;
    }

	public Geometry getTheGeom() {
        return theGeom;
    }

	public void setTheGeom(Geometry theGeom ) {
        this.theGeom = theGeom;
    }
}
