package com.thegist.domain;
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
@Table(schema = "overload",name = "route_address")
public class RouteAddress {

	@ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", nullable = false)
    private Address addressId;

	@ManyToOne
    @JoinColumn(name = "route_id", referencedColumnName = "route_id", nullable = false)
    private Route routeId;

	@Column(name = "sequence_number")
    @NotNull
    private Integer sequenceNumber;

	public Address getAddressId() {
        return addressId;
    }

	public void setAddressId(Address addressId) {
        this.addressId = addressId;
    }

	public Route getRouteId() {
        return routeId;
    }

	public void setRouteId(Route routeId) {
        this.routeId = routeId;
    }

	public Integer getSequenceNumber() {
        return sequenceNumber;
    }

	public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "route_address_id")
    private Integer routeAddressId;

	public Integer getRouteAddressId() {
        return this.routeAddressId;
    }

	public void setRouteAddressId(Integer id) {
        this.routeAddressId = id;
    }

	public String toString() {
        return new ReflectionToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).setExcludeFieldNames("addressId", "routeId").toString();
    }
}
