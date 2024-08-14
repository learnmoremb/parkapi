package zw.co.dreamhub.domain.models.ride;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ride_review")
public class RideReview extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "ride_id")
    private Ride ride;

    @Column(name = "rating")
    @Size(min = 0, max = 10)
    private double rating;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

}
