package com.example.demobot.Repository;

import com.example.demobot.model.Java.JavaAnswerAndQuestion;
import com.example.demobot.model.QA.QaAnswerAndQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QaAnswerAndQuestionRepository extends JpaRepository<QaAnswerAndQuestion, Long> {
    @Query(value = "SELECT * FROM qa_answer_and_question where qa_subtopics_id=? ",nativeQuery = true)
    List<QaAnswerAndQuestion> findBySubId(Long subId);
}