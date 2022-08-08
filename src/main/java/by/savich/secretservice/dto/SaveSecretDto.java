package by.savich.secretservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class SaveSecretDto {

    @NotEmpty(message = "secret information can't be empty")
    private String secretInformation;

    @NotEmpty(message = "pass phrase can't be empty")
    private String passPhrase;

    @Future
    private LocalDateTime validity;

    public String getSecretInformation() {
        return secretInformation;
    }

    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
    }

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }
}
