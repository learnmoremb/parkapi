package zw.co.dreamhub.domain.models.driver;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.InsurancePolicyType;

import java.time.ZonedDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "insurance")
public class Insurance extends BaseEntity {

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private InsurancePolicyType type;

    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "expiry_date")
    private ZonedDateTime expiryDate;

    @OneToOne
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
}
