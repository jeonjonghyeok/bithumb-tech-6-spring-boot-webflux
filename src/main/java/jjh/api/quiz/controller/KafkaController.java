package jjh.api.quiz.controller;

import jjh.api.quiz.domain.Consumers;
import jjh.api.quiz.domain.Producers;
import jjh.api.quiz.service.KafkaSender;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/kafka")
public class KafkaController {
    private final KafkaSender kafkaSender;
    private final Producers producers;
    @GetMapping
    public String hello() {
        return "Hello Kafka";
    }

    @GetMapping("/producer")
    public String producer(@RequestParam("message") String message){
        System.out.println("####################### producer 진입 ###################");
        kafkaSender.send(message);
        return "Message Sent to kafka Successfully";
    }

    @GetMapping("/receiver")
    public void receiver() {
        producers.sendMessage("quiz","Good-Luck");
    }
}
