package zw.co.dreamhub.domain.models.common;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@Table(name = "media")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Media extends BaseEntity {

    @Column(columnDefinition = "TEXT", name = "path")
    private String path;

    @Column(nullable = false, name = "content_type")
    private String contentType;

    @Column(nullable = false, columnDefinition = "TEXT", name = "name")
    private String name;

    public Media(String path, String name) {
        this.path = path;
        this.name = name;
    }
}
