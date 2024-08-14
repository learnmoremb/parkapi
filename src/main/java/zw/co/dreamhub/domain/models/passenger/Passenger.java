package zw.co.dreamhub.domain.models.passenger;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.Address;
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
@Table(name = "passenger")
public class Passenger extends BaseEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinTable(name = "passenger_addresses")
    private Set<Address> addresses = new HashSet<>(Collections.emptySet());

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "status_description", columnDefinition = "TEXT")
    private String statusDescription;

    public Passenger(User user) {
        this.user = user;
        this.status = Status.ACTIVE;
    }
}
