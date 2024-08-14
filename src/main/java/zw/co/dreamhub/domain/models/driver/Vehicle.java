package zw.co.dreamhub.domain.models.driver;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.Media;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vehicle")
public class Vehicle extends BaseEntity {

    @Column(name = "make", columnDefinition = "TEXT", nullable = false)
    private String make;

    @Column(name = "model", columnDefinition = "TEXT", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private long year;

    @Column(name = "color")
    private String color;

    @Column(columnDefinition = "TEXT", name = "description")
    private String description;

    @Column(name = "capacity", columnDefinition = "TEXT")
    private long capacity;

    @Column(name = "registrationNumber", unique = true)
    private String registrationNumber;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private VehicleCategory category;

    @Column(name = "engine_size")
    private double engineSize;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vehicle_images")
    private Set<Media> images = new HashSet<>(Collections.emptySet());

}
