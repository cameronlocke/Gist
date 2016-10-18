package com.thegist.domain;
import java.math.BigDecimal;
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
@Table(schema = "overload",name = "vehicle")
public class Vehicle {

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("vehicleLists", "organisationId").toString();
    }

	@OneToMany(mappedBy = "vehicleId")
    private Set<VehicleList> vehicleLists;

	@ManyToOne
    @JoinColumn(name = "organisation_id", referencedColumnName = "organisation_id")
    private Organisation organisationId;

	@Column(name = "registration_plate", length = 10)
    private String registrationPlate;

	@Column(name = "state_of_issue_id")
    private Integer stateOfIssueId;

	@Column(name = "vehicle_identfication_number", length = 20)
    private String vehicleIdentficationNumber;

	@Column(name = "chassis", length = 20)
    private String chassis;

	@Column(name = "engine", length = 20)
    private String engine;

	@Column(name = "external_reference", length = 30)
    private String externalReference;

	@Column(name = "gross_vehicle_mass", precision = 10, scale = 2)
    private BigDecimal grossVehicleMass;

	@Column(name = "gross_combination_mass", precision = 10, scale = 2)
    private BigDecimal grossCombinationMass;

	@Column(name = "aggregate_trailer_mass", precision = 10, scale = 2)
    private BigDecimal aggregateTrailerMass;

	@Column(name = "tare", precision = 10, scale = 2)
    private BigDecimal tare;

	@Column(name = "axles")
    private Short axles;

	@Column(name = "steer_axles")
    private Short steerAxles;

	@Column(name = "drive_axles")
    private Short driveAxles;

	@Column(name = "approved")
    private Boolean approved;

	public Set<VehicleList> getVehicleLists() {
        return vehicleLists;
    }

	public void setVehicleLists(Set<VehicleList> vehicleLists) {
        this.vehicleLists = vehicleLists;
    }

	public Organisation getOrganisationId() {
        return organisationId;
    }

	public void setOrganisationId(Organisation organisationId) {
        this.organisationId = organisationId;
    }

	public String getRegistrationPlate() {
        return registrationPlate;
    }

	public void setRegistrationPlate(String registrationPlate) {
        this.registrationPlate = registrationPlate;
    }

	public Integer getStateOfIssueId() {
        return stateOfIssueId;
    }

	public void setStateOfIssueId(Integer stateOfIssueId) {
        this.stateOfIssueId = stateOfIssueId;
    }

	public String getVehicleIdentficationNumber() {
        return vehicleIdentficationNumber;
    }

	public void setVehicleIdentficationNumber(String vehicleIdentficationNumber) {
        this.vehicleIdentficationNumber = vehicleIdentficationNumber;
    }

	public String getChassis() {
        return chassis;
    }

	public void setChassis(String chassis) {
        this.chassis = chassis;
    }

	public String getEngine() {
        return engine;
    }

	public void setEngine(String engine) {
        this.engine = engine;
    }

	public String getExternalReference() {
        return externalReference;
    }

	public void setExternalReference(String externalReference) {
        this.externalReference = externalReference;
    }

	public BigDecimal getGrossVehicleMass() {
        return grossVehicleMass;
    }

	public void setGrossVehicleMass(BigDecimal grossVehicleMass) {
        this.grossVehicleMass = grossVehicleMass;
    }

	public BigDecimal getGrossCombinationMass() {
        return grossCombinationMass;
    }

	public void setGrossCombinationMass(BigDecimal grossCombinationMass) {
        this.grossCombinationMass = grossCombinationMass;
    }

	public BigDecimal getAggregateTrailerMass() {
        return aggregateTrailerMass;
    }

	public void setAggregateTrailerMass(BigDecimal aggregateTrailerMass) {
        this.aggregateTrailerMass = aggregateTrailerMass;
    }

	public BigDecimal getTare() {
        return tare;
    }

	public void setTare(BigDecimal tare) {
        this.tare = tare;
    }

	public Short getAxles() {
        return axles;
    }

	public void setAxles(Short axles) {
        this.axles = axles;
    }

	public Short getSteerAxles() {
        return steerAxles;
    }

	public void setSteerAxles(Short steerAxles) {
        this.steerAxles = steerAxles;
    }

	public Short getDriveAxles() {
        return driveAxles;
    }

	public void setDriveAxles(Short driveAxles) {
        this.driveAxles = driveAxles;
    }

	public Boolean getApproved() {
        return approved;
    }

	public void setApproved(Boolean approved) {
        this.approved = approved;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "vehicle_id")
    private Integer vehicleId;

	public Integer getVehicleId() {
        return this.vehicleId;
    }

	public void setVehicleId(Integer id) {
        this.vehicleId = id;
    }
}
