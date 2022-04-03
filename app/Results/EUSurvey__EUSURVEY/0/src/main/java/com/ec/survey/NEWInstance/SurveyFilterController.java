package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SurveyFilterController {

 private SurveyFilter surveyfilter;

 private SurveyFilter surveyfilter;


@PutMapping
("/setUserDepartment")
public void setUserDepartment(@RequestParam(name = "userDepartment") String userDepartment){
surveyfilter.setUserDepartment(userDepartment);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "surveyType") String surveyType){
surveyfilter.setType(surveyType);
}


@PutMapping
("/setTitle")
public void setTitle(@RequestParam(name = "title") String title){
surveyfilter.setTitle(title);
}


@PutMapping
("/setSelector")
public void setSelector(@RequestParam(name = "selector") String selector){
surveyfilter.setSelector(selector);
}


@PutMapping
("/setFirstPublishedFrom")
public void setFirstPublishedFrom(@RequestParam(name = "firstPublishedFrom") Date firstPublishedFrom){
surveyfilter.setFirstPublishedFrom(firstPublishedFrom);
}


@PutMapping
("/setFirstPublishedTo")
public void setFirstPublishedTo(@RequestParam(name = "firstPublishedTo") Date firstPublishedTo){
surveyfilter.setFirstPublishedTo(firstPublishedTo);
}


@PutMapping
("/setGeneratedFrom")
public void setGeneratedFrom(@RequestParam(name = "generatedFrom") Date generatedFrom){
surveyfilter.setGeneratedFrom(generatedFrom);
}


@PutMapping
("/setGeneratedTo")
public void setGeneratedTo(@RequestParam(name = "generatedTo") Date generatedTo){
surveyfilter.setGeneratedTo(generatedTo);
}


@PutMapping
("/setEndFrom")
public void setEndFrom(@RequestParam(name = "endFrom") Date endFrom){
surveyfilter.setEndFrom(endFrom);
}


@PutMapping
("/setEndTo")
public void setEndTo(@RequestParam(name = "endTo") Date endTo){
surveyfilter.setEndTo(endTo);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") String status){
surveyfilter.setStatus(status);
}


@PutMapping
("/setDeleted")
public void setDeleted(@RequestParam(name = "deleted") Boolean deleted){
surveyfilter.setDeleted(deleted);
}


@PutMapping
("/setDeletedFrom")
public void setDeletedFrom(@RequestParam(name = "deletedFrom") Date deletedFrom){
surveyfilter.setDeletedFrom(deletedFrom);
}


@PutMapping
("/setDeletedTo")
public void setDeletedTo(@RequestParam(name = "deletedTo") Date deletedTo){
surveyfilter.setDeletedTo(deletedTo);
}


@PutMapping
("/setFrozen")
public void setFrozen(@RequestParam(name = "frozen") Boolean frozen){
surveyfilter.setFrozen(frozen);
}


@PutMapping
("/setMinReported")
public void setMinReported(@RequestParam(name = "minReported") Integer minReported){
surveyfilter.setMinReported(minReported);
}


@PutMapping
("/setMinContributions")
public void setMinContributions(@RequestParam(name = "minContributions") Integer minContributions){
surveyfilter.setMinContributions(minContributions);
}


}