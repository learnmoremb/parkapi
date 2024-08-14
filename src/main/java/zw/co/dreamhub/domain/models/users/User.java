package zw.co.dreamhub.domain.models.users;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.UserStatus;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 13/9/2023
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    @Column(unique = true, name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    private String country;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private UserStatus status = UserStatus.ACTIVE;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "detail_id")
    private UserDetail detail;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles"
//                        ,joinColumns = {@JoinColumn(name = "users_id"), @JoinColumn(name = "roles_id")}
    )
    private Set<Role> roles = new HashSet<>(Collections.emptySet());

    @ElementCollection
    @CollectionTable(name = "device_tokens", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "device_tokens", columnDefinition = "TEXT")
    @Fetch(FetchMode.JOIN)
    private Set<String> deviceTokens = new HashSet<>(Collections.emptySet());

}
