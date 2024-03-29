package by.savich.secretservice.controller;

import by.savich.secretservice.dto.ReadSecretDto;
import by.savich.secretservice.dto.SaveSecretDto;
import by.savich.secretservice.entity.Secret;
import by.savich.secretservice.service.SecretService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/secret")
public class SecretController {

    private final SecretService secretService;

    public SecretController(SecretService secretService) {
        this.secretService = secretService;
    }

    @PostMapping("/save")
    public Secret saveSecret(@Valid @RequestBody SaveSecretDto secret) {
        return secretService.saveSecret(secret);
    }

    @PostMapping("/read")
    public Secret readSecret(@Valid @RequestBody ReadSecretDto secret) {
        return secretService.getSecretByCodeAndPhrase(secret);
    }

}
