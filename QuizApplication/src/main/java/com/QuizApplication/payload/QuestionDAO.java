package com.QuizApplication.payload;

import com.QuizApplication.QuestionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionDAO extends JpaRepository<QuestionItem,Integer> {

  List<QuestionItem> findByCategory(String category);

  @Query(value = "SELECT * FROM Question_Item q WHERE q.category = :category ORDER BY RANDOM() LIMIT :numQ", nativeQuery = true)
  List<QuestionItem> findRandomQuestionByCategory(String category, int numQ);

}
