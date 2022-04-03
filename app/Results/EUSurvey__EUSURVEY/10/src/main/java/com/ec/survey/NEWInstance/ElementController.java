package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ElementController {

 private Element element;

 private Element element;


@GetMapping
("/isDelphiElement")
public boolean isDelphiElement(){
  return element.isDelphiElement();
}


@PutMapping
("/setUniqueId")
public void setUniqueId(@RequestParam(name = "uid") String uid){
element.setUniqueId(uid);
}


@PutMapping
("/setSurvey")
public void setSurvey(@RequestParam(name = "s") Survey s){
element.setSurvey(s);
}


@GetMapping
("/differsFrom")
public boolean differsFrom(@RequestParam(name = "element") Element element){
  return element.differsFrom(element);
}


@PutMapping
("/setSourceId")
public void setSourceId(@RequestParam(name = "sourceId") Integer sourceId){
element.setSourceId(sourceId);
}


@GetMapping
("/isUsedInResults")
public boolean isUsedInResults(){
  return element.isUsedInResults();
}


@PutMapping
("/setOriginalTitle")
public void setOriginalTitle(@RequestParam(name = "originalTitle") String originalTitle){
element.setOriginalTitle(originalTitle);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
element.setTitle(title);
}


@PutMapping
("/presetIsDependentMatrixQuestion")
public void presetIsDependentMatrixQuestion(@RequestParam(name = "survey") Survey survey){
element.presetIsDependentMatrixQuestion(survey);
}


@PutMapping
("/setLocked")
public void setLocked(@RequestParam(name = "locked") Boolean locked){
element.setLocked(locked);
}


@PutMapping
("/setPosition")
public void setPosition(@RequestParam(name = "position") Integer position){
element.setPosition(position);
}


}