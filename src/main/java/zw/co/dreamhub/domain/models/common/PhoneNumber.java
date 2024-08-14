package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 13/9/2023
 */
@Entity
@Table(name = "phone_number")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber extends BaseEntity {

    @Column(name = "number", unique = true)
    @Size(min = 12, max = 12)
    private String number;

}
