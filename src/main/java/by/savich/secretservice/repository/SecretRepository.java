package by.savich.secretservice.repository;

import by.savich.secretservice.entity.Secret;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface SecretRepository extends CrudRepository<Secret, Integer> {

    @Transactional
    void deleteByValidityBefore(LocalDateTime localDateTime);

    Optional<Secret> getSecretByGeneratedCodeAndPassPhraseAndValidityAfter(String code, String phrase,
                                                                           LocalDateTime localDateTime);

}
