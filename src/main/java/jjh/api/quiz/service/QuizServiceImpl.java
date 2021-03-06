package jjh.api.quiz.service;

import jjh.api.quiz.domain.Attempt;
import jjh.api.quiz.domain.Quiz;
import jjh.api.quiz.domain.User;
import jjh.api.quiz.repository.AttemptRepository;
import jjh.api.quiz.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Service @RequiredArgsConstructor
public class QuizServiceImpl implements QuizService{
    private final GeneratorService generatorService;
    private final UserRepository userRepository;
    private final AttemptRepository attemptRepository;

    public Mono<Quiz> createQuiz() {
        int factorA = generatorService.randomFactor();
        int factorB = generatorService.randomFactor();
        Quiz quiz = new Quiz(factorA, factorB);
        return Mono.just(quiz);
    }

    @Override
    public boolean checkAttempt(Attempt attempt) {
        // User Exist?
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        // Prevent Monipulation
        Assert.isTrue(!attempt.isCorrect(), "Unable to send in graded state");

        // Score
        boolean isCorrect = attempt.getResultAttempt() == attempt.getQuiz().getFactorA()
                * attempt.getQuiz().getFactorB();

        // Create Copy
        Attempt checkAttempt = new Attempt(attempt.getUser(), attempt.getQuiz(), attempt.getResultAttempt(), isCorrect);

        // Store
        attemptRepository.save(checkAttempt);

        return isCorrect;
    }

    @Override
    public Flux<Attempt> getStatusForUser(String alias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(alias);
    }

    @Override
    public Mono<Attempt> getResultById(long id) {
        return attemptRepository.findById(id);
    }
}
