package com.example.demobot.model.QA;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "qa_subtopics")
public class QaSubtopics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "subtopic",
            nullable = false
    )
    private String subtopic;
    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "qa_subtopics_id")
    private List<QaAnswerAndQuestion> questionList;
}
