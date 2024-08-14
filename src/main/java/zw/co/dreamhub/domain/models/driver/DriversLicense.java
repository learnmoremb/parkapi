package zw.co.dreamhub.domain.models.driver;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.Media;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drivers_license")
@Entity
public class DriversLicense extends BaseEntity {

    @Column(name = "license_number", nullable = false, columnDefinition = "TEXT")
    private String licenseNumber;

    @Column(name = "date_obtained", nullable = false)
    private LocalDate dateObtained;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "expiry_date")
    private ZonedDateTime expiryDate;

    @Column(name = "country", nullable = false)
    private String country;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "drivers_license_images")
    private Set<Media> images = new HashSet<>(Collections.emptySet());
}
