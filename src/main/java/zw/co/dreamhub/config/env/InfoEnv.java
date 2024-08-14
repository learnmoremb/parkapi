package zw.co.dreamhub.config.env;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/6/2022
 */


@ConfigurationProperties(prefix = "info")
public record InfoEnv(
        MediaConfig media,
        App app,
        Contact contact,
        Url url,
        Security security
) {

    public record Url(
            String unSecured,
            String secured
    ) {
    }

    public record App(
            String name,
            String description,
            double version,
            String basePackage
    ) {
    }

    public record Contact(
            String name,
            String url,
            String email
    ) {
    }

    public record Security(
            Password password,
            Jwt jwt
    ) {

        public record Password(
                String salt,
                String saltChars
        ) {
        }

        public record Jwt(
                String secret,
                long jwtExpirationMs,
                long jwtRefreshExpirationMs
        ) {
        }
    }


    public record MediaConfig(
            String dir
    ) {
    }
}
