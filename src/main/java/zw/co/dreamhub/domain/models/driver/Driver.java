package zw.co.dreamhub.domain.models.driver;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.common.Address;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.Status;
import zw.co.dreamhub.domain.models.users.User;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class Driver extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(name = "driver_licenses")
    private Set<DriversLicense> licenses = new HashSet<>(Collections.emptySet());

    @OneToMany
    @JoinTable(name = "driver_vehicles")
    private Set<Vehicle> vehicles = new HashSet<>(Collections.emptySet());

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "status_description", columnDefinition = "TEXT")
    private String statusDescription;

    @Column(name = "is_available")
    private boolean isAvailable;

    public Driver(User user) {
        this.user = user;
        // todo : create an endpoint that updates status & isAvailable
        this.status = Status.ACTIVE;
        this.isAvailable = true;
        // todo : ensure address is not null for both driver & passenger
    }
}
