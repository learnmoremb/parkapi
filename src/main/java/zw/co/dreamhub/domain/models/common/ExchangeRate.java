package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;
import zw.co.dreamhub.domain.models.BaseEntity;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 17/1/2024
 */


@Table(name = "exchange_rate")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class ExchangeRate extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "amount_id")
    private Amount amount;

    public ExchangeRate(Amount amount) {
        this.amount = amount;
    }

}
