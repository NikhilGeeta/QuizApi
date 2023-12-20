package com.QuizApplication.Service;

import com.QuizApplication.QuestionItem;
import com.QuizApplication.QuestionWrapper;
import com.QuizApplication.Quiz;
import com.QuizApplication.Response;
import com.QuizApplication.payload.QuestionDAO;
import com.QuizApplication.payload.QuizDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDAO quizDAO;

    @Autowired
    QuestionDAO questionDAO;
    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
        List<QuestionItem> questionItems = questionDAO.findRandomQuestionByCategory(category,numQ);

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestion(questionItems);

        quizDAO.save(quiz);
        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> byId = quizDAO.findById(id);
        List<QuestionItem> questionDB = byId.get().getQuestion();

        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(QuestionItem q:questionDB){
            QuestionWrapper qu = new QuestionWrapper(q.getId(),q.getQuestionTitle(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionsForUser.add(qu);
        }
        return new ResponseEntity<>(questionsForUser,HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz = quizDAO.findById(id).get();
        List<QuestionItem> question = quiz.getQuestion();

        int right=0;
        int i=0;
        for(Response response:responses){
         if (response.getResponse().equals(question.get(i).getRightAnswer())){
                i++;
                right++;
            }
        }
        return new ResponseEntity<>(right,HttpStatus.OK);
    }
}
