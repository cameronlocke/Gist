package com.thegist.domain;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "axle")
public class Axle {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("axleConfigurationId").toString();
    }

	@ManyToOne
    @JoinColumn(name = "axle_configuration_id", referencedColumnName = "axle_configuration_id", nullable = false)
    private AxleConfiguration axleConfigurationId;

	@Column(name = "axle_number")
    @NotNull
    private Integer axleNumber;

	@Column(name = "mass", precision = 10, scale = 2)
    private BigDecimal mass;

	@Column(name = "tyres")
    private Integer tyres;

	@Column(name = "tyre_width")
    private Integer tyreWidth;

	@Column(name = "ground_contact_length", precision = 10, scale = 2)
    private BigDecimal groundContactLength;

	@Column(name = "ground_contact_width", precision = 10, scale = 2)
    private BigDecimal groundContactWidth;

	public AxleConfiguration getAxleConfigurationId() {
        return axleConfigurationId;
    }

	public void setAxleConfigurationId(AxleConfiguration axleConfigurationId) {
        this.axleConfigurationId = axleConfigurationId;
    }

	public Integer getAxleNumber() {
        return axleNumber;
    }

	public void setAxleNumber(Integer axleNumber) {
        this.axleNumber = axleNumber;
    }

	public BigDecimal getMass() {
        return mass;
    }

	public void setMass(BigDecimal mass) {
        this.mass = mass;
    }

	public Integer getTyres() {
        return tyres;
    }

	public void setTyres(Integer tyres) {
        this.tyres = tyres;
    }

	public Integer getTyreWidth() {
        return tyreWidth;
    }

	public void setTyreWidth(Integer tyreWidth) {
        this.tyreWidth = tyreWidth;
    }

	public BigDecimal getGroundContactLength() {
        return groundContactLength;
    }

	public void setGroundContactLength(BigDecimal groundContactLength) {
        this.groundContactLength = groundContactLength;
    }

	public BigDecimal getGroundContactWidth() {
        return groundContactWidth;
    }

	public void setGroundContactWidth(BigDecimal groundContactWidth) {
        this.groundContactWidth = groundContactWidth;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "axle_id")
    private Integer axleId;

	public Integer getAxleId() {
        return this.axleId;
    }

	public void setAxleId(Integer id) {
        this.axleId = id;
    }
}
