package jjh.api.quiz.domain;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumers {
    @KafkaListener(topics="quiz")
    public void listenGroup(String message) {
        System.out.println("Received Message Test: "+message);
    }
}
