package com.thegist.domain;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "model_vehicle")
public class ModelVehicle {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "model_vehicle_id")
    private Integer modelVehicleId;

	public Integer getModelVehicleId() {
        return this.modelVehicleId;
    }

	public void setModelVehicleId(Integer id) {
        this.modelVehicleId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationRoadManagerConsents", "vehicleCombinationId").toString();
    }

	@OneToMany(mappedBy = "modelVehicleId")
    private Set<ApplicationRoadManagerConsent> applicationRoadManagerConsents;

	@ManyToOne
    @JoinColumn(name = "vehicle_combination_id", referencedColumnName = "vehicle_combination_id")
    private VehicleCombination vehicleCombinationId;

	@Column(name = "name")
    private String name;

	@Column(name = "description")
    private String description;

	public Set<ApplicationRoadManagerConsent> getApplicationRoadManagerConsents() {
        return applicationRoadManagerConsents;
    }

	public void setApplicationRoadManagerConsents(Set<ApplicationRoadManagerConsent> applicationRoadManagerConsents) {
        this.applicationRoadManagerConsents = applicationRoadManagerConsents;
    }

	public VehicleCombination getVehicleCombinationId() {
        return vehicleCombinationId;
    }

	public void setVehicleCombinationId(VehicleCombination vehicleCombinationId) {
        this.vehicleCombinationId = vehicleCombinationId;
    }

	public String getName() {
        return name;
    }

	public void setName(String name) {
        this.name = name;
    }

	public String getDescription() {
        return description;
    }

	public void setDescription(String description) {
        this.description = description;
    }
}
