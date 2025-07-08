package com.algovis.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Entity
@Setter @Getter @ToString
@NoArgsConstructor
@Table(name = "quiz")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "algorithm_id", nullable = false)
    private Algorithm algorithm;
    private String question;
    @ElementCollection
    @CollectionTable(name = "quiz_answers", joinColumns = @JoinColumn(name = "quiz_id"))
    @Column(name = "answer")
    private List<String> answers;
    private int correctAnswerIndex;
}
