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
import javax.validation.constraints.NotNull;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "vehicle_list")
public class VehicleList {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("vehicleCombinations", "vehicleId").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_list_id")
    private Integer vehicleListId;

	public Integer getVehicleListId() {
        return this.vehicleListId;
    }

	public void setVehicleListId(Integer id) {
        this.vehicleListId = id;
    }

	@OneToMany(mappedBy = "vehicleListId")
    private Set<VehicleCombination> vehicleCombinations;

	@ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "vehicle_id", nullable = false)
    private Vehicle vehicleId;

	@Column(name = "vehicle_combination_id")
    @NotNull
    private Integer vehicleCombinationId;

	@Column(name = "position")
    @NotNull
    private Integer position;

	public Set<VehicleCombination> getVehicleCombinations() {
        return vehicleCombinations;
    }

	public void setVehicleCombinations(Set<VehicleCombination> vehicleCombinations) {
        this.vehicleCombinations = vehicleCombinations;
    }

	public Vehicle getVehicleId() {
        return vehicleId;
    }

	public void setVehicleId(Vehicle vehicleId) {
        this.vehicleId = vehicleId;
    }

	public Integer getVehicleCombinationId() {
        return vehicleCombinationId;
    }

	public void setVehicleCombinationId(Integer vehicleCombinationId) {
        this.vehicleCombinationId = vehicleCombinationId;
    }

	public Integer getPosition() {
        return position;
    }

	public void setPosition(Integer position) {
        this.position = position;
    }
}
