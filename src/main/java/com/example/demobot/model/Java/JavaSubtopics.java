package com.example.demobot.model.Java;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "java_subtopics")
@Data
public class JavaSubtopics {
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
    @JoinColumn(name = "java_subtopics_id")
    private List<JavaAnswerAndQuestion> questionList;
}
