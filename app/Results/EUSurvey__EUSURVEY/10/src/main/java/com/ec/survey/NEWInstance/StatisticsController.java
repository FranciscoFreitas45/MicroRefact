package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StatisticsController {

 private Statistics statistics;

 private Statistics statistics;


@PutMapping
("/setRequestID")
public void setRequestID(@RequestParam(name = "requestID") Integer requestID){
statistics.setRequestID(requestID);
}


@PutMapping
("/setSurveyId")
public void setSurveyId(@RequestParam(name = "surveyId") int surveyId){
statistics.setSurveyId(surveyId);
}


}