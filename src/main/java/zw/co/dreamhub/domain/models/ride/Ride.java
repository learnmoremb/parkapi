package zw.co.dreamhub.domain.models.ride;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.Address;
import zw.co.dreamhub.domain.models.driver.Driver;
import zw.co.dreamhub.domain.models.enums.RideStatus;
import zw.co.dreamhub.domain.models.passenger.Passenger;
import zw.co.dreamhub.domain.models.common.Payment;
import zw.co.dreamhub.domain.models.driver.Vehicle;

import java.time.ZonedDateTime;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ride")
public class Ride extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ride_type_id")
    private RideType rideType;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "ride_passengers")
    private Set<Passenger> passengers = new HashSet<>(Collections.emptySet());

    @ManyToOne
    @JoinColumn(name = "pick_up_address_id")
    private Address pickUpAddress;

    @ManyToOne
    @JoinColumn(name = "drop_off_address_id")
    private Address dropOffAddress;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "pick_up_time")
    private ZonedDateTime pickUpTime;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "drop_of_time")
    private ZonedDateTime dropOfTime;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private RideStatus status;

    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    public Ride(RideType rideType, Set<Passenger> passengers,
                Address pickUpAddress, Address dropOffAddress,
                Payment payment){
        this.rideType = rideType;
        this.passengers = passengers;
        this.status = RideStatus.REQUESTED;
        this.pickUpAddress = pickUpAddress;
        this.dropOffAddress = dropOffAddress;
        this.payment = payment;
    }

    public void acceptRide(Driver driver, Vehicle vehicle, RideStatus status){
        this.driver = driver;
        this.vehicle = vehicle;
        this.status = status;
    }
}
