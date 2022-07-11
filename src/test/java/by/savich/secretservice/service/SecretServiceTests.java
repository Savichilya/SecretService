package by.savich.secretservice.service;

import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.service.RandomGenerator;
import by.savich.secretservice.repository.SecretRepository;
import by.savich.secretservice.service.SecretService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecretServiceTests {

    SecretRepository secretRepository;
    SecretService secretService;
    RandomGenerator randomGenerator;

    @BeforeEach
    void init() {
        secretRepository = Mockito.mock(SecretRepository.class);
        randomGenerator = Mockito.mock(RandomGenerator.class);
        secretService = new SecretService(secretRepository, randomGenerator);
    }

    @Test
    void shouldSaveSecretWhenCallSaveMethod() {
        Secret secret = new Secret();
        secretService.saveSecret(secret);
        verify(secretRepository).save(secret);
    }

    @Test
    void shouldReturnSecretFromRepositoryWhenCallSecretByGeneratedCodeAndPassPhrase() {
        Secret secret = new Secret();
        when(secretRepository.getSecretByGeneratedCodeAndPassPhrase("12345", "terminator")).thenReturn(secret);
        secret.setGeneratedCode("12345");
        secret.setPassPhrase("terminator");
        Secret resultSecret = secretService.getSecretByCodeAndPhrase(secret);
        assertThat(resultSecret).isEqualTo(secret);
    }
}
