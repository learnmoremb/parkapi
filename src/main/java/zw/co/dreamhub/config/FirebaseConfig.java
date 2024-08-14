package zw.co.dreamhub.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import zw.co.dreamhub.config.env.FirebaseEnv;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author sheltons
 * Email sheltshamu@gmail.com
 * Created on 2024/01/07
 */

@Configuration
@RequiredArgsConstructor
@Slf4j
public class FirebaseConfig {

    //Todo: Add file path for the firebase json file
    private final FirebaseEnv env;

    //    @PostConstruct
    public void initialize() {
        try (FileInputStream serviceAcc = new FileInputStream(env.getFilePath())) {
            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAcc))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            log.info("Firebase Initialization Exception : {}", e.getMessage());
            throw new RuntimeException("Failed to initialize firebase");
        }
    }
}