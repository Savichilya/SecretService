package by.savich.secretService.repository;

import by.savich.secretService.entity.Secret;
import org.springframework.data.repository.CrudRepository;

public interface SecretRepository extends CrudRepository<Secret, Integer> {

    Secret getSecretByGeneratedCodeAndPassPhrase(String code, String phrase);

}
