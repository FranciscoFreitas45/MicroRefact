package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class NumberQuestionController {

 private NumberQuestion numberquestion;

 private NumberQuestion numberquestion;


@GetMapping
("/isSlider")
public boolean isSlider(){
  return numberquestion.isSlider();
}


@GetMapping
("/showStatisticsForNumberQuestion")
public boolean showStatisticsForNumberQuestion(){
  return numberquestion.showStatisticsForNumberQuestion();
}


@PutMapping
("/setUnit")
public void setUnit(@RequestParam(name = "unit") String unit){
numberquestion.setUnit(unit);
}


@PutMapping
("/setMinLabel")
public void setMinLabel(@RequestParam(name = "minLabel") String minLabel){
numberquestion.setMinLabel(minLabel);
}


@PutMapping
("/setMaxLabel")
public void setMaxLabel(@RequestParam(name = "maxLabel") String maxLabel){
numberquestion.setMaxLabel(maxLabel);
}


}