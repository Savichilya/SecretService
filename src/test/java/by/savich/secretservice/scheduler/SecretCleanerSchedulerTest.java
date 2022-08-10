package by.savich.secretservice.scheduler;

import by.savich.secretservice.repository.SecretRepository;
import by.savich.secretservice.service.SecretService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;


class SecretCleanerSchedulerTest {
    SecretCleanerScheduler secretCleanerScheduler;
    SecretService secretService;

    @BeforeEach
    void init() {
        secretService = Mockito.mock(SecretService.class);
        secretCleanerScheduler = new SecretCleanerScheduler(secretService);
    }

    @Test
    void shouldDeleteSecretWhenCallRemovingSecretsMethod(){
        secretCleanerScheduler.removingSecrets();
        verify(secretService).cleanExpiredSecrets();
    }
}