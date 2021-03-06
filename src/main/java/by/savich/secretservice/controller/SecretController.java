package by.savich.secretservice.controller;

import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.service.SecretService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secret")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/save")
    public Secret saveSecret(@RequestBody Secret secret) {
        return secretService.saveSecret(secret);
    }

    @PostMapping("/read")
    public Secret readSecret(@RequestBody Secret secret) {
        return secretService.getSecretByCodeAndPhrase(secret);
    }
}
