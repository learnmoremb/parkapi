package zw.co.dreamhub.domain.models.users;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.common.PhoneNumber;
import zw.co.dreamhub.domain.models.enums.Gender;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 13/9/2023
 */
@Entity
@Table(name = "user_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetail extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(columnDefinition = "TEXT", name = "middle_names")
    private String middleNames;

    @Column(name = "last_name")
    private String lastName;

    @Email
    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_detail_phone_numbers")
    private Set<PhoneNumber> phoneNumbers = new HashSet<>(Collections.emptySet());

}
