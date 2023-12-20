package com.QuizApplication.payload;

import com.QuizApplication.Quiz;
import com.QuizApplication.Service.QuizService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizDAO extends JpaRepository<Quiz,Integer> {

}
