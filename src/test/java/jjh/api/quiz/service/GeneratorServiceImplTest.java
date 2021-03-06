package jjh.api.quiz.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class GeneratorServiceImplTest {
    @Mock GeneratorService generatorService;
    @BeforeEach
    void setUp() {
        generatorService = new GeneratorServiceImpl();

    }

    @DisplayName("Check valid Generator Service")
    @Test
    void randomFactor() {
    }
}