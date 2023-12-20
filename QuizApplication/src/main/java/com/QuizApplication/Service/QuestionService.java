package com.QuizApplication.Service;

import com.QuizApplication.QuestionItem;
import com.QuizApplication.payload.QuestionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class QuestionService {

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<ArrayList> getAllQuestion() {
        try {

            new ResponseEntity<>(  questionDAO.findAll(), HttpStatus.CREATED);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<?> getQuestionsByCategory(String category) {
        try {
            return new ResponseEntity<>(questionDAO.findByCategory(category),HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_REQUEST);
    }

    public String addQuestions(QuestionItem questionItem) {
        try {
            questionDAO.save(questionItem);
            return "Question is saved :";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "Question is not saved Issued not occur";
    }
}
