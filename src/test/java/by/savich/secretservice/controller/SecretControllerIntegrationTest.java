package by.savich.secretservice.controller;

import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.service.RandomGenerator;
import by.savich.secretservice.repository.SecretRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecretControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private SecretRepository secretRepository;

    @Autowired
    private RandomGenerator randomGenerator;

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

        HttpEntity<Secret> httpEntity = new HttpEntity<>(secret);
        ResponseEntity<Secret> response = this.restTemplate.exchange(host + "/secret/save",
                HttpMethod.POST, httpEntity, Secret.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(response.getBody().getGeneratedCode()).isNotNull();
        assertThat(response.getBody().getSecretInformation()).isEqualTo("any information");
        assertThat(response.getBody().getPassPhrase()).isEqualTo("java");

        assertThat(secretRepository.findAll()).isNotNull();

    }

    @Test
    void shouldGetSecretByGeneratedCodeAndPassPhrase() {
        Secret secret = new Secret();

        secret.setSecretInformation("any information");
        secret.setPassPhrase("java");
        secret.setGeneratedCode(randomGenerator.generateRandomCode(12));
        secretRepository.save(secret);

        Secret checkSecret = new Secret();
        checkSecret.setPassPhrase("java");
        checkSecret.setGeneratedCode(secret.getGeneratedCode());

        HttpEntity<Secret> httpEntity = new HttpEntity<>(checkSecret);
        Secret resultSecret = this.restTemplate.exchange(host + "/secret/read", HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<Secret>() {
                }).getBody();

        ResponseEntity<Secret> resultSecret2 = this.restTemplate.exchange(host + "/secret/read", HttpMethod.POST,
                httpEntity, new ParameterizedTypeReference<Secret>() {
                });
        assertThat(resultSecret2.getStatusCode()).isEqualTo(HttpStatus.OK);

        assertThat(resultSecret).isEqualTo(secret);
    }

}