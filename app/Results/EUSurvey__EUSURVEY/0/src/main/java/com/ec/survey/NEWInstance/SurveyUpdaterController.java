package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SurveyUpdaterController {

 private SurveyUpdater surveyupdater;


@PutMapping
("/run")
public void run(){
surveyupdater.run();
}


}