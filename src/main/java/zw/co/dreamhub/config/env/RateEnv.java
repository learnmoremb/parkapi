package zw.co.dreamhub.config.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/08
 */

@ConfigurationProperties(prefix = "rate")
@Component
@Data
public class RateEnv {
    private BigDecimal rate;
}
