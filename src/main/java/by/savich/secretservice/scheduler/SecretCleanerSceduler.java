package by.savich.secretservice.scheduler;

import by.savich.secretservice.service.SecretService;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

public class SecretCleanerSceduler {

    private final SecretService secretService;

    public SecretCleanerSceduler(SecretService secretService) {
        this.secretService = secretService;
    }
//jjj
    @Scheduled
    public void removingSecrets(LocalDateTime localDateTime) {
        secretService.cleanExpiredSecrets(localDateTime);
    }
}
