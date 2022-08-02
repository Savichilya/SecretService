package by.savich.secretservice.scheduler;

import by.savich.secretservice.service.SecretService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SecretCleanerScheduler {

    private final static Logger log = LoggerFactory.getLogger(SecretCleanerScheduler.class);

    private final SecretService secretService;

    public SecretCleanerScheduler(SecretService secretService) {
        this.secretService = secretService;
    }

    @Scheduled(fixedDelay = 5000)
    public void removingSecrets() {
        log.info("Class instance is: {}", this);
        secretService.cleanExpiredSecrets();
    }
}
