package com.QuizApplication.Controller;

import com.QuizApplication.QuestionItem;
import com.QuizApplication.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/allQuestions")
    public ResponseEntity<ArrayList> getAllQuestions(){
        return questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<?> getQuestionsByCategory(@PathVariable String category){
        return questionService.getQuestionsByCategory(category);
    }

    @PostMapping("/addQuestions")
    public String addQuestion(@RequestBody QuestionItem questionItem){
        return questionService.addQuestions(questionItem);
    }
}
