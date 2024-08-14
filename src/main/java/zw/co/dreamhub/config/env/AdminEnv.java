package zw.co.dreamhub.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 16/10/2023
 */

@ConfigurationProperties(prefix = "admin-details")
public record AdminEnv(
        String username,
        String password,
        String phoneNumber

) {
}
