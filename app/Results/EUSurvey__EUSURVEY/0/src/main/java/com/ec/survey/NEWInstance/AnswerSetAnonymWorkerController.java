package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class AnswerSetAnonymWorkerController {

 private AnswerSetAnonymWorker answersetanonymworker;


@PutMapping
("/run")
public void run(){
answersetanonymworker.run();
}


}