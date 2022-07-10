package by.savich.secretService.controller;

import by.savich.secretService.entity.Secret;
import by.savich.secretService.repository.RandomString;
import by.savich.secretService.repository.SecretRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecretControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private RandomString randomString;

    @Autowired
    private TestRestTemplate restTemplate;

    private String host;

    @BeforeEach
    void init() {
        host = "http://localhost:" + port;
    }

    @BeforeEach
    void clearDatabase() {
        secretRepository.deleteAll();
    }

    @Test
    void shouldSaveSecret() {
        Secret secret = new Secret();
        secret.setSecretInformation("any information");
        secret.setPassPhrase("java");
        secret.setGeneratedCode(randomString.randomCode(12));
        secretRepository.save(secret);

        HttpEntity<Secret> httpEntity = new HttpEntity<>(secret);
        ResponseEntity<Void> response = this.restTemplate.exchange(host + "/secret/saveSecret",
                HttpMethod.POST, httpEntity, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldGetSecretByGeneratedCodeAndPassPhrase() {
        Secret secret = new Secret();

        secret.setSecretInformation("any information");
        secret.setPassPhrase("java");
        secret.setGeneratedCode(randomString.randomCode(12));
        secretRepository.save(secret);

        Secret checkSecret = new Secret();
        checkSecret.setPassPhrase("java");
        checkSecret.setGeneratedCode(secret.getGeneratedCode());

        HttpEntity<Secret> httpEntity = new HttpEntity<>(checkSecret);
        Secret resultSecret = this.restTemplate.exchange(host + "/secret/getSecret", HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<Secret>() {
                }).getBody();

        assertThat(resultSecret).isEqualTo(secret);
    }

}