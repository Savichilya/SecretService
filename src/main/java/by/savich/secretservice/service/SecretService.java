package by.savich.secretservice.service;

import by.savich.secretservice.dto.ReadSecretDto;
import by.savich.secretservice.dto.SaveSecretDto;
import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.exception.SecretNotFoundException;
import by.savich.secretservice.repository.SecretRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    private final RandomGenerator randomGenerator;

    public SecretService(SecretRepository secretRepository, RandomGenerator randomGenerator) {
        this.secretRepository = secretRepository;
        this.randomGenerator = randomGenerator;
    }

    public Secret saveSecret(SaveSecretDto secretDto) {
        Secret secret = new Secret();
        secret.setSecretInformation(secretDto.getSecretInformation());
        secret.setPassPhrase(secretDto.getPassPhrase());
        secret.setValidity(secretDto.getValidity());
        secret.setGeneratedCode(randomGenerator.generateRandomCode(12));
        return secretRepository.save(secret);
    }

    public void cleanExpiredSecrets() {
        secretRepository.deleteByValidityBefore(LocalDateTime.now());
    }

    public Secret getSecretByCodeAndPhrase(ReadSecretDto secretDto) {
        return secretRepository.getSecretByGeneratedCodeAndPassPhraseAndValidityAfter(secretDto.getGeneratedCode(),
                secretDto.getPassPhrase(), LocalDateTime.now()).orElseThrow(() ->
                new SecretNotFoundException("Secret not found, data is incorrect"));
    }

}
