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
@Table(schema = "overload",name = "vehicle_combination")
public class VehicleCombination {

	@OneToMany(mappedBy = "vehicleCombinationId")
    private Set<ApplicationArea> applicationAreas;

	@OneToMany(mappedBy = "vehicleCombinationId")
    private Set<ApplicationRoute> applicationRoutes;

	@OneToMany(mappedBy = "vehicleCombinationId")
    private Set<ApplicationVehicle> applicationVehicles;

	@OneToMany(mappedBy = "vehicleCombinationId")
    private Set<ModelVehicle> modelVehicles;

	@ManyToOne
    @JoinColumn(name = "axle_configuration_id", referencedColumnName = "axle_configuration_id")
    private AxleConfiguration axleConfigurationId;

	@ManyToOne
    @JoinColumn(name = "dimension_id", referencedColumnName = "dimension_id")
    private Dimension dimensionId;

	@ManyToOne
    @JoinColumn(name = "vehicle_list_id", referencedColumnName = "vehicle_list_id", nullable = false)
    private VehicleList vehicleListId;

	@Column(name = "road_access")
    private Integer roadAccess;

	public Set<ApplicationArea> getApplicationAreas() {
        return applicationAreas;
    }

	public void setApplicationAreas(Set<ApplicationArea> applicationAreas) {
        this.applicationAreas = applicationAreas;
    }

	public Set<ApplicationRoute> getApplicationRoutes() {
        return applicationRoutes;
    }

	public void setApplicationRoutes(Set<ApplicationRoute> applicationRoutes) {
        this.applicationRoutes = applicationRoutes;
    }

	public Set<ApplicationVehicle> getApplicationVehicles() {
        return applicationVehicles;
    }

	public void setApplicationVehicles(Set<ApplicationVehicle> applicationVehicles) {
        this.applicationVehicles = applicationVehicles;
    }

	public Set<ModelVehicle> getModelVehicles() {
        return modelVehicles;
    }

	public void setModelVehicles(Set<ModelVehicle> modelVehicles) {
        this.modelVehicles = modelVehicles;
    }

	public AxleConfiguration getAxleConfigurationId() {
        return axleConfigurationId;
    }

	public void setAxleConfigurationId(AxleConfiguration axleConfigurationId) {
        this.axleConfigurationId = axleConfigurationId;
    }

	public Dimension getDimensionId() {
        return dimensionId;
    }

	public void setDimensionId(Dimension dimensionId) {
        this.dimensionId = dimensionId;
    }

	public VehicleList getVehicleListId() {
        return vehicleListId;
    }

	public void setVehicleListId(VehicleList vehicleListId) {
        this.vehicleListId = vehicleListId;
    }

	public Integer getRoadAccess() {
        return roadAccess;
    }

	public void setRoadAccess(Integer roadAccess) {
        this.roadAccess = roadAccess;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("applicationAreas", "applicationRoutes", "applicationVehicles", "modelVehicles", "axleConfigurationId", "dimensionId", "vehicleListId").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_combination_id")
    private Integer vehicleCombinationId;

	public Integer getVehicleCombinationId() {
        return this.vehicleCombinationId;
    }

	public void setVehicleCombinationId(Integer id) {
        this.vehicleCombinationId = id;
    }
}
