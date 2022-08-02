package by.savich.secretservice.repository;

import by.savich.secretservice.entity.Secret;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public interface SecretRepository extends CrudRepository<Secret, Integer> {

    @Transactional
    void deleteByValidityBefore(LocalDateTime localDateTime);

    Secret getSecretByGeneratedCodeAndPassPhrase(String code, String phrase);

}
