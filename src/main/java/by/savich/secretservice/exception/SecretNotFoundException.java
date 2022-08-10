package by.savich.secretservice.exception;

public class SecretNotFoundException extends RuntimeException {

    public SecretNotFoundException(String message) {
        super(message);
    }
}
