package zw.co.dreamhub.domain.models.users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.UserRole;


/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 13/9/2023
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private UserRole type;

}
