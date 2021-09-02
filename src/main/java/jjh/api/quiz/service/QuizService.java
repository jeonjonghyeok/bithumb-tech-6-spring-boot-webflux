package jjh.api.quiz.service;

import jjh.api.quiz.domain.Attempt;
import jjh.api.quiz.domain.Quiz;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface QuizService {
    Mono<Quiz> createQuiz();

    boolean checkAttempt(Attempt attempt);

    Flux<Attempt> getStatusForUser(String alias);

    Mono<Attempt> getResultById(long id);
}
