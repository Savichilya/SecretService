package by.savich.secretservice.scheduler;

import by.savich.secretservice.service.SecretService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class SecretCleanerSceduler {

    private final SecretService secretService;

    public SecretCleanerSceduler(SecretService secretService) {
        this.secretService = secretService;
    }

    @Scheduled
    public void removingSecrets(LocalDateTime localDateTime) {
        secretService.cleanExpiredSecrets(localDateTime);
    }
}
