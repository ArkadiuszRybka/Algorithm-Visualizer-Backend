package com.algovis.backend.service.impl;

import com.algovis.backend.model.dto.QuizAnswerDto;
import com.algovis.backend.model.dto.QuizResultDto;
import com.algovis.backend.model.dto.QuizSelectionDto;
import com.algovis.backend.model.dto.request.QuizSubmitRequst;
import com.algovis.backend.model.entity.*;
import com.algovis.backend.repository.*;
import com.algovis.backend.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class QuizServiceImpl implements QuizService {
    private final QuizRepository quizRepository;
    private final AlgorithmRepository algorithmRepository;
    private final UserRepository userRepository;
    private final QuizAnswerRepository quizAnswerRepository;
    private final QuizResultRepository quizResultRepository;
    private final UserProgressRepository userProgressRepository;

    public QuizServiceImpl(QuizRepository quizRepository, AlgorithmRepository algorithmRepository, UserRepository userRepository, QuizAnswerRepository quizAnswerRepository, QuizResultRepository quizResultRepository, UserProgressRepository userProgressRepository) {
        this.quizRepository = quizRepository;
        this.algorithmRepository = algorithmRepository;
        this.userRepository = userRepository;
        this.quizAnswerRepository = quizAnswerRepository;
        this.quizResultRepository = quizResultRepository;
        this.userProgressRepository = userProgressRepository;
    }

    @Override
    public List<Quiz> findByAlgorithmIdShuffled(Long algorithmId) {
        List<Quiz> quizzes = new ArrayList<>(quizRepository.findByAlgorithmId(algorithmId));
        Collections.shuffle(quizzes);
        for(Quiz q : quizzes){
            List<QuizAnswer> answers = new ArrayList<>(q.getAnswers());
            Collections.shuffle(answers);
            q.getAnswers().clear();
            q.getAnswers().addAll(answers);
        }
        return quizzes;
    }

    @Override
    @Transactional
    public QuizResultDto evaluateAndSaveResult(Long algorithmId, String userEmail, QuizSubmitRequst requst) {
        Algorithm algorithm = algorithmRepository.findById(algorithmId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Algorithm not found"));
        User user = userRepository.findByEmail(userEmail)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Quiz> quizzes = quizRepository.findByAlgorithmId(algorithmId);
        int total = quizzes.size();

        if(requst == null || requst.getAnswers() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answers are required");
        }

        Map<Long, Quiz> quizById = quizzes.stream()
                .collect(Collectors.toMap(Quiz::getId, q -> q));

        int correct = 0;
        for(QuizSelectionDto selection : requst.getAnswers()){
            if(selection.getQuizId() == null || selection.getSelectedAnswerId() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "quizId and selectionAsnwerId are required");
            }

            Quiz quiz = quizById.get(selection.getQuizId());
            if(quiz == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Quiz does not belong to this algorithm");
            }

            Optional<QuizAnswer> fromLoaded = quiz.getAnswers().stream()
                    .filter(a -> Objects.equals(a.getId(), selection.getSelectedAnswerId()))
                    .findFirst();

            QuizAnswer answer = fromLoaded.orElseGet(() ->
                    quizAnswerRepository.findById(selection.getSelectedAnswerId())
                            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answer not found")));

            if(!Objects.equals(answer.getQuiz().getId(), quiz.getId())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Answer does not match quiz");
            }

            if(answer.isCorrect()) correct++;
        }

        QuizResult quizResult = new QuizResult();
        quizResult.setUser(user);
        quizResult.setAlgorithm(algorithm);
        quizResult.setCorrectAnswers(correct);
        quizResult.setTotalQuestions(total);
        quizResult.setTimestamp(LocalDateTime.now());
        quizResultRepository.save(quizResult);

        updateUserProgress(user, algorithm, correct, total);

        return new QuizResultDto(correct, total);
    }

    private void updateUserProgress(User user, Algorithm algorithm, int correct, int total){
        boolean full = total > 0 && correct == total;
        String target = full ? "COMPLETED" : "IN_PROGRESS";

        UserProgress userProgress = userProgressRepository
                .findByUserIdAndAlgorithmId(user.getId(), algorithm.getId())
                .orElse(null);

        if(userProgress==null){
            userProgress.setUser(user);
            userProgress.setAlgorithm(algorithm);
            userProgress.setProgressStatus(target);
            userProgressRepository.save(userProgress);
            return;
        }

        if(!"COMPLETED".equals(userProgress.getProgressStatus())){
            userProgress.setProgressStatus(target);
            userProgressRepository.save(userProgress);
        }
    }
}
