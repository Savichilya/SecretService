package by.savich.secretservice.service;

import by.savich.secretservice.dto.ReadSecretDto;
import by.savich.secretservice.dto.SaveSecretDto;
import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.repository.SecretRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SecretService {

    private final static Logger log = LoggerFactory.getLogger(SecretService.class);

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

    public void cleanExpiredSecrets(LocalDateTime localDateTime) {
        log.info("Class instance is: {}", this);
        secretRepository.deleteByValidityBefore(localDateTime);
    }

    public Secret getSecretByCodeAndPhrase(ReadSecretDto secretDto) {
        return secretRepository.getSecretByGeneratedCodeAndPassPhrase(secretDto.getGeneratedCode(), secretDto.getPassPhrase());
    }

}
