package com.algovis.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "algorithm_id", nullable = false)
    @ToString.Exclude
    private Algorithm algorithm;

    @Column(nullable = false, length = 255)
    private String question;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @ToString.Exclude
    private List<QuizAnswer> answers = new ArrayList<>();

    public void addAnswer(QuizAnswer answer) {
        answers.add(answer);
        answer.setQuiz(this);
    }

    public void removeAnswer(QuizAnswer answer) {
        answers.remove(answer);
        answer.setQuiz(null);
    }
}
