package by.savich.secretservice.service;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomGenerator {

    static private final String CHARACTER_SEQUENCE = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private final SecureRandom secureRandom = new SecureRandom();

    public String randomCode(int size){
        StringBuilder sb = new StringBuilder(size);
        for(int i = 0; i < size; i++)
            sb.append(CHARACTER_SEQUENCE.charAt(secureRandom.nextInt(CHARACTER_SEQUENCE.length())));
        return sb.toString();
    }
}
