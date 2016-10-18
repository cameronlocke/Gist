package com.thegist.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Entity
@Table(schema = "overload",name = "application_road_manager_consent")
public class ApplicationRoadManagerConsent {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("application", "loadId", "modelVehicleId", "routeId").toString();
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "application_id")
    private Integer applicationId;

	public Integer getApplicationId() {
        return this.applicationId;
    }

	public void setApplicationId(Integer id) {
        this.applicationId = id;
    }

	@OneToOne
    @JoinColumn(name = "application_id", nullable = false, insertable = false, updatable = false)
    private Application application;

	@ManyToOne
    @JoinColumn(name = "load_id", referencedColumnName = "load_id")
    private Load loadId;

	@ManyToOne
    @JoinColumn(name = "model_vehicle_id", referencedColumnName = "model_vehicle_id")
    private ModelVehicle modelVehicleId;

	@ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private Route routeId;

	public Application getApplication() {
        return application;
    }

	public void setApplication(Application application) {
        this.application = application;
    }

	public Load getLoadId() {
        return loadId;
    }

	public void setLoadId(Load loadId) {
        this.loadId = loadId;
    }

	public ModelVehicle getModelVehicleId() {
        return modelVehicleId;
    }

	public void setModelVehicleId(ModelVehicle modelVehicleId) {
        this.modelVehicleId = modelVehicleId;
    }

	public Route getRouteId() {
        return routeId;
    }

	public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }
}
