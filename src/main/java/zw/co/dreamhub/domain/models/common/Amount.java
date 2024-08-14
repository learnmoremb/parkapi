package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.*;
import lombok.*;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.Currency;

import java.math.BigDecimal;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "amount")
@Builder
public class Amount extends BaseEntity {

    @Column(name = "value")
    private BigDecimal value;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;
}
