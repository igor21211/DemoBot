package com.example.demobot.Repository;

import com.example.demobot.model.Java.JavaAnswerAndQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JavaAnswerAndQuestionRepository extends JpaRepository<JavaAnswerAndQuestion, Long> {
    @Query(value = "SELECT * FROM java_answer_and_question where java_subtopics_id=? ",nativeQuery = true)
    List<JavaAnswerAndQuestion> findBySubId(Long subId);


}