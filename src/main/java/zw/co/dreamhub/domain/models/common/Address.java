package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import zw.co.dreamhub.domain.models.BaseEntity;


@Table(name = "address")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Address extends BaseEntity {

    @Column(name = "street", columnDefinition = "TEXT")
    private String street;

    @Column(name = "suburb", columnDefinition = "TEXT")
    private String suburb;

    @Column(name = "country")
    private String country;

}
