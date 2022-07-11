package by.savich.secretservice.service;

import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    private final RandomGenerator randomGenerator;

    public SecretService(SecretRepository secretRepository, RandomGenerator randomGenerator) {
        this.secretRepository = secretRepository;
        this.randomGenerator = randomGenerator;
    }

    public Secret saveSecret(Secret secret) {
        secret.setGeneratedCode(randomGenerator.randomCode(12));
        return secretRepository.save(secret);
    }

    public Secret getSecretByCodeAndPhrase(Secret secret) {
        return secretRepository.getSecretByGeneratedCodeAndPassPhrase(secret.getGeneratedCode(), secret.getPassPhrase());
    }

}
