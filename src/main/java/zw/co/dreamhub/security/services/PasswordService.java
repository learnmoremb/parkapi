package zw.co.dreamhub.security.services;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import zw.co.dreamhub.config.env.InfoEnv;

import java.util.Random;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 14/3/2023
 */
@Component
@RequiredArgsConstructor
public class PasswordService {

    final InfoEnv env;

    @Bean
    public String randomPassword() {

        StringBuilder salt = new StringBuilder();
        Random random = new Random();

        while (salt.length() < 6) {
            salt.append(env.security()
                    .password()
                    .saltChars()
                    .toLowerCase()
                    .charAt(random
                            .nextInt(env.security()
                                    .password()
                                    .saltChars()
                                    .length())));
        }

        return salt.toString();

    }

}
