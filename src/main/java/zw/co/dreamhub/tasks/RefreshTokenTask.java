package zw.co.dreamhub.tasks;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import zw.co.dreamhub.domain.repositories.users.TokenRefreshRepository;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 19/9/2023
 */

@Configuration
@EnableScheduling
@Slf4j
@RequiredArgsConstructor
public class RefreshTokenTask {

    private final TokenRefreshRepository refreshRepository;

    // Second : Minute : Hour : Day of month : Day(s) of week

    @Scheduled(cron = "0 0 2 * * *")
    void deleteExpiredTokens() {
        long count = refreshRepository.deleteAllByIsValidFalse();
        log.info("Deleted : {} expired refresh tokens", count);
    }

}
