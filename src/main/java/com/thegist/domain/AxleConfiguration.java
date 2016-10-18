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
@Table(schema = "overload",name = "axle_configuration")
public class AxleConfiguration {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "axle_configuration_id")
    private Integer axleConfigurationId;

	public Integer getAxleConfigurationId() {
        return this.axleConfigurationId;
    }

	public void setAxleConfigurationId(Integer id) {
        this.axleConfigurationId = id;
    }

	@OneToMany(mappedBy = "axleConfigurationId")
    private Set<Axle> axles;

	@OneToMany(mappedBy = "axleConfigurationId")
    private Set<VehicleCombination> vehicleCombinations;

	@Column(name = "axle_group")
    private Integer axleGroup;

	@Column(name = "number_of_axles")
    private Integer numberOfAxles;

	@Column(name = "gross_mass", precision = 10, scale = 2)
    private BigDecimal grossMass;

	public Set<Axle> getAxles() {
        return axles;
    }

	public void setAxles(Set<Axle> axles) {
        this.axles = axles;
    }

	public Set<VehicleCombination> getVehicleCombinations() {
        return vehicleCombinations;
    }

	public void setVehicleCombinations(Set<VehicleCombination> vehicleCombinations) {
        this.vehicleCombinations = vehicleCombinations;
    }

	public Integer getAxleGroup() {
        return axleGroup;
    }

	public void setAxleGroup(Integer axleGroup) {
        this.axleGroup = axleGroup;
    }

	public Integer getNumberOfAxles() {
        return numberOfAxles;
    }

	public void setNumberOfAxles(Integer numberOfAxles) {
        this.numberOfAxles = numberOfAxles;
    }

	public BigDecimal getGrossMass() {
        return grossMass;
    }

	public void setGrossMass(BigDecimal grossMass) {
        this.grossMass = grossMass;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("axles", "vehicleCombinations").toString();
    }
}
