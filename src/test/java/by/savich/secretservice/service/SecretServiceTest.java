package by.savich.secretservice.service;

import by.savich.secretservice.dto.ReadSecretDto;
import by.savich.secretservice.dto.SaveSecretDto;
import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.exception.SecretNotFoundException;
import by.savich.secretservice.repository.SecretRepository;
import by.savich.secretservice.scheduler.SecretCleanerScheduler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecretServiceTest {

    SecretRepository secretRepository;
    SecretService secretService;
    RandomGenerator randomGenerator;
    SecretCleanerScheduler secretCleanerScheduler;

    @BeforeEach
    void init() {
        secretRepository = Mockito.mock(SecretRepository.class);
        randomGenerator = Mockito.mock(RandomGenerator.class);
        secretCleanerScheduler = Mockito.mock(SecretCleanerScheduler.class);
        secretService = new SecretService(secretRepository, randomGenerator);
    }

    @Test
    void shouldSaveSecretWhenCallSaveMethod() {
        Secret secret = new Secret();
        SaveSecretDto saveSecretDto = new SaveSecretDto();
        saveSecretDto.setSecretInformation("qwerty");
        saveSecretDto.setPassPhrase("pass");
        secret.setSecretInformation("qwerty");
        secret.setPassPhrase("pass");
        secretService.saveSecret(saveSecretDto);
        verify(secretRepository).save(secret);
    }

    @Test
    void shouldDeleteSecretWhenCallCleanExpiredSecretsMethod() {
        secretService.cleanExpiredSecrets();
        verify(secretRepository).deleteByValidityBefore(any(LocalDateTime.class));
    }

    @Test
    void shouldReturnSecretFromRepositoryWhenCallSecretByGeneratedCodeAndPassPhrase() {
        Optional<Secret> secret = Optional.of(new Secret());
        secret.get().setGeneratedCode("12345");
        secret.get().setPassPhrase("terminator");
        secret.get().setValidity(LocalDateTime.parse("2022-08-04T22:00"));

        ReadSecretDto readSecretDto = new ReadSecretDto();
        readSecretDto.setGeneratedCode("12345");
        readSecretDto.setPassPhrase("terminator");

        when(secretRepository.getSecretByGeneratedCodeAndPassPhraseAndValidityAfter(anyString(), anyString(),
                any(LocalDateTime.class))).thenReturn(secret);

        Secret resultSecret = secretService.getSecretByCodeAndPhrase(readSecretDto);
        assertThat(resultSecret).isEqualTo(secret.get());
    }

    @Test
    void shouldExceptionIsThrownWhenReturnSecretFromRepositoryWhenCallSecretByGeneratedCodeAndPassPhrase() {
        ReadSecretDto readSecretDto = new ReadSecretDto();
        readSecretDto.setGeneratedCode("12345");
        readSecretDto.setPassPhrase("terminator");

        when(secretRepository.getSecretByGeneratedCodeAndPassPhraseAndValidityAfter(anyString(), anyString(),
                any(LocalDateTime.class))).thenReturn(Optional.empty());

        assertThrows(SecretNotFoundException.class, () -> secretService.getSecretByCodeAndPhrase(readSecretDto));
    }

}
