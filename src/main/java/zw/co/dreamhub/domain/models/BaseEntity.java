package zw.co.dreamhub.domain.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 13/9/2023
 */

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @CreationTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "date_created")
    private ZonedDateTime dateCreated;

    @UpdateTimestamp
    @Column(columnDefinition = "TIMESTAMP WITH TIME ZONE", name = "last_updated")
    private ZonedDateTime lastUpdated;

}
