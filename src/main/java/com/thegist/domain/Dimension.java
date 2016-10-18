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
@Table(schema = "overload",name = "dimension")
public class Dimension {

	@OneToMany(mappedBy = "dimensionId")
    private Set<VehicleCombination> vehicleCombinations;

	@Column(name = "length", precision = 10, scale = 2)
    private BigDecimal length;

	@Column(name = "width", precision = 10, scale = 2)
    private BigDecimal width;

	@Column(name = "height", precision = 10, scale = 2)
    private BigDecimal height;

	@Column(name = "front_overhang", precision = 10, scale = 2)
    private BigDecimal frontOverhang;

	@Column(name = "rear_overhang", precision = 10, scale = 2)
    private BigDecimal rearOverhang;

	@Column(name = "front_projection", precision = 10, scale = 2)
    private BigDecimal frontProjection;

	@Column(name = "rear_projection", precision = 10, scale = 2)
    private BigDecimal rearProjection;

	@Column(name = "lhs_projection", precision = 10, scale = 2)
    private BigDecimal lhsProjection;

	@Column(name = "rhs_projection", precision = 10, scale = 2)
    private BigDecimal rhsProjection;

	public Set<VehicleCombination> getVehicleCombinations() {
        return vehicleCombinations;
    }

	public void setVehicleCombinations(Set<VehicleCombination> vehicleCombinations) {
        this.vehicleCombinations = vehicleCombinations;
    }

	public BigDecimal getLength() {
        return length;
    }

	public void setLength(BigDecimal length) {
        this.length = length;
    }

	public BigDecimal getWidth() {
        return width;
    }

	public void setWidth(BigDecimal width) {
        this.width = width;
    }

	public BigDecimal getHeight() {
        return height;
    }

	public void setHeight(BigDecimal height) {
        this.height = height;
    }

	public BigDecimal getFrontOverhang() {
        return frontOverhang;
    }

	public void setFrontOverhang(BigDecimal frontOverhang) {
        this.frontOverhang = frontOverhang;
    }

	public BigDecimal getRearOverhang() {
        return rearOverhang;
    }

	public void setRearOverhang(BigDecimal rearOverhang) {
        this.rearOverhang = rearOverhang;
    }

	public BigDecimal getFrontProjection() {
        return frontProjection;
    }

	public void setFrontProjection(BigDecimal frontProjection) {
        this.frontProjection = frontProjection;
    }

	public BigDecimal getRearProjection() {
        return rearProjection;
    }

	public void setRearProjection(BigDecimal rearProjection) {
        this.rearProjection = rearProjection;
    }

	public BigDecimal getLhsProjection() {
        return lhsProjection;
    }

	public void setLhsProjection(BigDecimal lhsProjection) {
        this.lhsProjection = lhsProjection;
    }

	public BigDecimal getRhsProjection() {
        return rhsProjection;
    }

	public void setRhsProjection(BigDecimal rhsProjection) {
        this.rhsProjection = rhsProjection;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("vehicleCombinations").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "dimension_id")
    private Integer dimensionId;

	public Integer getDimensionId() {
        return this.dimensionId;
    }

	public void setDimensionId(Integer id) {
        this.dimensionId = id;
    }
}
