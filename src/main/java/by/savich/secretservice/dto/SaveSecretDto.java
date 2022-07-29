package by.savich.secretservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class SaveSecretDto {
    private String secretInformation;
    private String passPhrase;
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

    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    public LocalDateTime getValidity() {
        return validity;
    }

    public void setValidity(LocalDateTime validity) {
        this.validity = validity;
    }
}
