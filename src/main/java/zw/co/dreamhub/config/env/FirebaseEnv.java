package zw.co.dreamhub.config.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */

@ConfigurationProperties(prefix = "firebase")
@Component
@Data
public class FirebaseEnv {
    private String filePath;
}
