package com.algovis.backend.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(
        name = "quiz_answer",
        uniqueConstraints = @UniqueConstraint(columnNames = {"quiz_id", "text"})
)
@Getter
@Setter
@ToString
@NoArgsConstructor
public class QuizAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id", nullable = false)
    @ToString.Exclude
    private Quiz quiz;

    @Column(nullable = false, length = 255)
    private String text;

    @Column(name = "is_correct", nullable = false)
    private boolean correct;
}
