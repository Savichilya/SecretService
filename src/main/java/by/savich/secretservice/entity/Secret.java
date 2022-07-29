package by.savich.secretservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Secret")
public class Secret {

    private String secretInformation;
    private String passPhrase;
    private String generatedCode;
    private LocalDateTime validity;

    @Id
    @Column(name = "generated_code", length = 15)
    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    @Column(name = "secret_information", length = 500, nullable = false)
    public String getSecretInformation() {
        return secretInformation;
    }

    public void setSecretInformation(String secretInformation) {
        this.secretInformation = secretInformation;
    }

    @Column(name = "passphrase", length = 50, nullable = false)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Secret)) return false;
        Secret secret = (Secret) o;
        return Objects.equals(secretInformation, secret.secretInformation) && Objects.equals(passPhrase, secret.passPhrase) && Objects.equals(generatedCode, secret.generatedCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secretInformation, passPhrase, generatedCode);
    }
}
