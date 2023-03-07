package com.example.demobot.model.QA;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "qa_answer_and_question")
@Data
public class QaAnswerAndQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "question",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR(255)"
    )
    private String question;
    @Column(
            name = "answer",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR(10000)"
    )
    private String answer;
    @ManyToOne
    private QaSubtopics QaSubtopics;
}
