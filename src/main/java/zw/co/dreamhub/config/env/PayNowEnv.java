package zw.co.dreamhub.config.env;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 24/1/2023
 */

@ConfigurationProperties(prefix = "paynow")
@Component
@Data
public class PayNowEnv {
    private String resultUrl;
    private String returnUrl;
    private Integration integration;

    @Data
    public static class Integration {
        private String id;
        private String key;
    }
}
