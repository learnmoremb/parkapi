package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zw.co.dreamhub.domain.models.BaseEntity;
import zw.co.dreamhub.domain.models.enums.Relationship;
import zw.co.dreamhub.domain.models.enums.Status;
import zw.co.dreamhub.domain.models.users.User;
import zw.co.dreamhub.domain.models.users.UserDetail;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "emergency_contact")
public class EmergencyContact extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "contact_id")
    private UserDetail contact;

    @Column(name = "relationship")
    @Enumerated(EnumType.STRING)
    private Relationship relationship;

    @Column(name = "other_description", columnDefinition = "TEXT")
    private String otherDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

}
