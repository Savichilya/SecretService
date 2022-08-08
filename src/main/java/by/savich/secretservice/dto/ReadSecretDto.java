package by.savich.secretservice.dto;

import javax.validation.constraints.NotEmpty;

public class ReadSecretDto {

    @NotEmpty(message = "pass phrase can't be empty")
    private String passPhrase;

    @NotEmpty(message = "generated code can't be empty")
    private String generatedCode;

    public String getPassPhrase() {
        return passPhrase;
    }

    public void setPassPhrase(String passPhrase) {
        this.passPhrase = passPhrase;
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }
}
