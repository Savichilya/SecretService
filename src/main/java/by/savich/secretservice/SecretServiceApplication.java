package by.savich.secretservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SecretServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SecretServiceApplication.class, args);
    }
}
