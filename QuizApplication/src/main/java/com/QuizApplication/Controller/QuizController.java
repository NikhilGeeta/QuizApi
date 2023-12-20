package com.QuizApplication.Controller;

import com.QuizApplication.QuestionItem;
import com.QuizApplication.QuestionWrapper;
import com.QuizApplication.Response;
import com.QuizApplication.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;
    // http://localhost:8080/quiz/create?category&numQ&title
    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam String category,@RequestParam int numQ,@RequestParam String title){
        return quizService.createQuiz(category,numQ,title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id){
       return quizService.getQuizQuestion(id);
    }

    @PostMapping("/submit/{id}")
    public  ResponseEntity<Integer> submitQuiz(@PathVariable Integer id,@RequestBody List<Response> response){
        return quizService.calculateResult(id,response);
    }
}
