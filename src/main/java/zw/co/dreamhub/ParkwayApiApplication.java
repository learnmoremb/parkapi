package zw.co.dreamhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import zw.co.dreamhub.config.env.AdminEnv;
import zw.co.dreamhub.config.env.EmailEnv;
import zw.co.dreamhub.config.env.InfoEnv;

@SpringBootApplication
@EnableConfigurationProperties({AdminEnv.class, EmailEnv.class, InfoEnv.class})
public class ParkwayApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParkwayApiApplication.class, args);
    }

}
