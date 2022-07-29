package by.savich.secretservice.repository;

import by.savich.secretservice.entity.Secret;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;

public interface SecretRepository extends CrudRepository<Secret, Integer> {
    Secret removeSecretByValidity(LocalDateTime localDateTime);

    Secret getSecretByGeneratedCodeAndPassPhrase(String code, String phrase);

}
