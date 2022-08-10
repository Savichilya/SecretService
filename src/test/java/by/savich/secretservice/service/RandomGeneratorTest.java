package by.savich.secretservice.service;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomGeneratorTest {

    @Test
    void shouldGenerateRandomWhenCallRandomCodeMethod() {
        RandomGenerator randomGenerator = new RandomGenerator();
        String code=randomGenerator.generateRandomCode(12);
        assertThat(code).isNotNull();
        assertThat(code.length()).isEqualTo(12);
    }

}