package by.savich.secretService.service;

import by.savich.secretService.entity.Secret;
import by.savich.secretService.repository.RandomString;
import by.savich.secretService.repository.SecretRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SecretService {

    private final SecretRepository secretRepository;

    @Autowired
    private final RandomString randomString;

    public SecretService(SecretRepository secretRepository, RandomString randomString) {
        this.secretRepository = secretRepository;
        this.randomString = randomString;
    }

    public void saveSecret(Secret secret) {
        secret.setGeneratedCode(randomString.randomCode(12));
        secretRepository.save(secret);
    }

    public Secret getSecretByCodeAndPhrase(Secret secret) {
        return secretRepository.getSecretByGeneratedCodeAndPassPhrase(secret.getGeneratedCode(), secret.getPassPhrase());
    }

}
