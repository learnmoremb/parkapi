package zw.co.dreamhub.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 17/6/2022
 */

@ConfigurationProperties(prefix = "spring")
public record EmailEnv(
        Mail mail
) {

    public record Mail(
            String host,
            int port,
            String username,
            String password
    ) {
    }
}
