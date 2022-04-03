package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ValidCodesServiceController {

 private ValidCodesService validcodesservice;


@GetMapping
("/checkValid")
public boolean checkValid(@RequestParam(name = "uniqueCode") String uniqueCode,@RequestParam(name = "surveyUid") String surveyUid){
  return validcodesservice.checkValid(uniqueCode,surveyUid);
}


@PutMapping
("/add")
public void add(@RequestParam(name = "uniqueCode") String uniqueCode,@RequestParam(name = "survey") Survey survey){
validcodesservice.add(uniqueCode,survey);
}


@PutMapping
("/revalidate")
public void revalidate(@RequestParam(name = "uniqueCode") String uniqueCode,@RequestParam(name = "survey") Survey survey){
validcodesservice.revalidate(uniqueCode,survey);
}


@PutMapping
("/invalidate")
public void invalidate(@RequestParam(name = "uniqueCode") String uniqueCode){
validcodesservice.invalidate(uniqueCode);
}


}