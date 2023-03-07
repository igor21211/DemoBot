package com.example.demobot.model.Java;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "java_answer_and_question")
@Data
public class JavaAnswerAndQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "question",
            nullable = false,
            unique = true,
            columnDefinition = "VARCHAR(1000)"
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
    private JavaSubtopics javaSubtopics;


}
