package com.ec.survey.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ArchiveController {

 private Archive archive;


@PutMapping
("/setFinished")
public void setFinished(@RequestParam(name = "finished") boolean finished){
archive.setFinished(finished);
}


@PutMapping
("/setError")
public void setError(@RequestParam(name = "error") String error){
archive.setError(error);
}


@PutMapping
("/setCreated")
public void setCreated(@RequestParam(name = "created") Date created){
archive.setCreated(created);
}


@PutMapping
("/setSurveyTitle")
public void setSurveyTitle(@RequestParam(name = "surveyTitle") String surveyTitle){
archive.setSurveyTitle(surveyTitle);
}


@PutMapping
("/setSurveyUID")
public void setSurveyUID(@RequestParam(name = "surveyUID") String surveyUID){
archive.setSurveyUID(surveyUID);
}


@PutMapping
("/setReplies")
public void setReplies(@RequestParam(name = "replies") Integer replies){
archive.setReplies(replies);
}


@PutMapping
("/setSurveyHasUploadedFiles")
public void setSurveyHasUploadedFiles(@RequestParam(name = "surveyHasUploadedFiles") Boolean surveyHasUploadedFiles){
archive.setSurveyHasUploadedFiles(surveyHasUploadedFiles);
}


@PutMapping
("/setSurveyShortname")
public void setSurveyShortname(@RequestParam(name = "surveyShortname") String surveyShortname){
archive.setSurveyShortname(surveyShortname);
}


@PutMapping
("/setOwner")
public void setOwner(@RequestParam(name = "owner") String owner){
archive.setOwner(owner);
}


@PutMapping
("/setUserId")
public void setUserId(@RequestParam(name = "userId") int userId){
archive.setUserId(userId);
}


@PutMapping
("/setLanguages")
public void setLanguages(@RequestParam(name = "languages") String languages){
archive.setLanguages(languages);
}


@PutMapping
("/setArchived")
public void setArchived(@RequestParam(name = "archived") Date archived){
archive.setArchived(archived);
}


}