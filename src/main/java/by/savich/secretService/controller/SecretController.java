package by.savich.secretService.controller;

import by.savich.secretService.entity.Secret;
import by.savich.secretService.service.SecretService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/secret")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/saveSecret")
    public void saveSecret(@RequestBody Secret secret) {
        secretService.saveSecret(secret);
    }

    @PostMapping("/getSecret")
    public Secret getSecret(@RequestBody Secret secret) {
        return secretService.getSecretByCodeAndPhrase(secret);
    }
}
