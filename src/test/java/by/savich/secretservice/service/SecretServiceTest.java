package by.savich.secretservice.service;

import by.savich.secretservice.dto.ReadSecretDto;
import by.savich.secretservice.dto.SaveSecretDto;
import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.repository.SecretRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class SecretServiceTest {

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
        SaveSecretDto saveSecretDto=new SaveSecretDto();
        saveSecretDto.setSecretInformation("qwerty");
        saveSecretDto.setPassPhrase("pass");
        secret.setSecretInformation("qwerty");
        secret.setPassPhrase("pass");
        secretService.saveSecret(saveSecretDto);
        verify(secretRepository).save(secret);
    }

    @Test
    void shouldReturnSecretFromRepositoryWhenCallSecretByGeneratedCodeAndPassPhrase() {
        Secret secret = new Secret();
        ReadSecretDto readSecretDto=new ReadSecretDto();
        when(secretRepository.getSecretByGeneratedCodeAndPassPhrase("12345", "terminator")).
                thenReturn(secret);
        secret.setGeneratedCode("12345");
        secret.setPassPhrase("terminator");
        readSecretDto.setGeneratedCode("12345");
        readSecretDto.setPassPhrase("terminator");
        Secret resultSecret = secretService.getSecretByCodeAndPhrase(readSecretDto);
        assertThat(resultSecret).isEqualTo(secret);
    }
}
