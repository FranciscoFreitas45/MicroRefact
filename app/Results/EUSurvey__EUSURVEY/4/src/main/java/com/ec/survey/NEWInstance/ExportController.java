package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ExportController {

 private Export export;

 private Export export;


@GetMapping
("/isAllAnswers")
public boolean isAllAnswers(){
  return export.isAllAnswers();
}


}