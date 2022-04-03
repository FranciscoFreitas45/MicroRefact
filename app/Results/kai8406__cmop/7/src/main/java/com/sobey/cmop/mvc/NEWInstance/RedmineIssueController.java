package com.sobey.cmop.mvc.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class RedmineIssueController {

 private RedmineIssue redmineissue;

 private RedmineIssue redmineissue;


@PutMapping
("/setTrackerId")
public void setTrackerId(@RequestParam(name = "trackerId") Integer trackerId){
redmineissue.setTrackerId(trackerId);
}


@PutMapping
("/setSubject")
public void setSubject(@RequestParam(name = "subject") String subject){
redmineissue.setSubject(subject);
}


@PutMapping
("/setAssignee")
public void setAssignee(@RequestParam(name = "assignee") Integer assignee){
redmineissue.setAssignee(assignee);
}


@PutMapping
("/setStatus")
public void setStatus(@RequestParam(name = "status") Integer status){
redmineissue.setStatus(status);
}


@PutMapping
("/setIssueId")
public void setIssueId(@RequestParam(name = "issueId") Integer issueId){
redmineissue.setIssueId(issueId);
}


@PutMapping
("/setResourceId")
public void setResourceId(@RequestParam(name = "resourceId") String resourceId){
redmineissue.setResourceId(resourceId);
}


@PutMapping
("/setServiceTagId")
public void setServiceTagId(@RequestParam(name = "serviceTagId") Integer serviceTagId){
redmineissue.setServiceTagId(serviceTagId);
}


@PutMapping
("/setApplyId")
public void setApplyId(@RequestParam(name = "applyId") Integer applyId){
redmineissue.setApplyId(applyId);
}


}