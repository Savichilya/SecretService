package by.savich.secretservice.repository;

import by.savich.secretservice.entity.Secret;
import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<Secret, Integer> {

    Secret getSecretByGeneratedCodeAndPassPhrase(String code, String phrase);

}
