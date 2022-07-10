package by.savich.secretService;

import by.savich.secretService.entity.Secret;
import by.savich.secretService.repository.RandomString;
import by.savich.secretService.repository.SecretRepository;
import by.savich.secretService.service.SecretService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class SecretServiceApplicationTests {

    SecretRepository secretRepository;
    SecretService secretService;
    RandomString randomString;

    @BeforeEach
    void init() {
        secretRepository = Mockito.mock(SecretRepository.class);
        randomString = Mockito.mock(RandomString.class);
        secretService = new SecretService(secretRepository, randomString);
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
