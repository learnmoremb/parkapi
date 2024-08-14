package zw.co.dreamhub.boostrap;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import zw.co.dreamhub.config.env.AdminEnv;
import zw.co.dreamhub.domain.dto.request.users.PhoneNumberRequest;
import zw.co.dreamhub.domain.dto.request.users.UserDetailRequest;
import zw.co.dreamhub.domain.dto.request.users.UserRequest;
import zw.co.dreamhub.domain.models.enums.UserRole;
import zw.co.dreamhub.services.users.UserService;

import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 6/12/2023
 */


@Component
@Slf4j
@RequiredArgsConstructor
public class BootStrap implements CommandLineRunner {

    private final AdminEnv adminEnv;
    private final UserService service;

    private static final String NAME = "DEFAULT USER CREATION";
    private static final String USER_NAME = "Admin";

    private final ObjectMapper mapper;

    @Override
    public void run(String... args) {

        try {

            var response = service.register(
                    new UserRequest(
                            adminEnv.password(),
                            "Zimbabwe",
                            adminEnv.username(),
                            new UserDetailRequest(
                                    USER_NAME,
                                    USER_NAME,
                                    USER_NAME,
                                    adminEnv.username(),
                                    Set.of(new PhoneNumberRequest(adminEnv.phoneNumber()))
                            )
                    ),
                    UserRole.SUPER_ADMIN);

            log.info("{} response : {}", NAME, mapper.writeValueAsString(response));
        } catch (Exception e) {
            log.info("{} exception : {}", NAME, e.getMessage());
        }

    }


}
