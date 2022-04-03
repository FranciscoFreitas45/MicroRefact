package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkItemController {

 private WorkItem workitem;


@GetMapping
("/getWorkStreams")
public List<String> getWorkStreams(){
  return workitem.getWorkStreams();
}


@GetMapping
("/toString")
public String toString(){
  return workitem.toString();
}


@GetMapping
("/getId")
public int getId(){
  return workitem.getId();
}


@GetMapping
("/isCompleted")
public boolean isCompleted(){
  return workitem.isCompleted();
}


@GetMapping
("/isExcluded")
public boolean isExcluded(){
  return workitem.isExcluded();
}


@PutMapping
("/setAverageCaseEstimate")
public void setAverageCaseEstimate(@RequestParam(name = "averageCaseEstimate") int averageCaseEstimate){
workitem.setAverageCaseEstimate(averageCaseEstimate);
}


@PutMapping
("/setWorstCaseEstimate")
public void setWorstCaseEstimate(@RequestParam(name = "worstCaseEstimate") int worstCaseEstimate){
workitem.setWorstCaseEstimate(worstCaseEstimate);
}


@PutMapping
("/setMustHave")
public void setMustHave(@RequestParam(name = "mustHave") boolean mustHave){
workitem.setMustHave(mustHave);
}


@PutMapping
("/addComment")
public void addComment(@RequestParam(name = "comment") WorkItemComment comment){
workitem.addComment(comment);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
workitem.setName(name);
}


@GetMapping
("/isBlocked")
public boolean isBlocked(){
  return workitem.isBlocked();
}


@PutMapping
("/setNotes")
public void setNotes(@RequestParam(name = "notes") String notes){
workitem.setNotes(notes);
}


@PutMapping
("/setExcluded")
public void setExcluded(@RequestParam(name = "excluded") boolean excluded){
workitem.setExcluded(excluded);
}


@PutMapping
("/setColour")
public void setColour(@RequestParam(name = "colour") String colour){
workitem.setColour(colour);
}


@PutMapping
("/setWorkStreamsAsString")
public void setWorkStreamsAsString(@RequestParam(name = "workStream") String workStream){
workitem.setWorkStreamsAsString(workStream);
}


@PutMapping
("/setDate")
public void setDate(@RequestParam(name = "phase") String phase,@RequestParam(name = "date") LocalDate date){
workitem.setDate(phase,date);
}


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "object") Object object){
  return workitem.equals(object);
}


@PutMapping
("/advance")
public void advance(@RequestParam(name = "date") LocalDate date){
workitem.advance(date);
}


@PutMapping
("/stop")
public void stop(){
workitem.stop();
}


@PutMapping
("/resetCommentsAndReplaceWith")
public void resetCommentsAndReplaceWith(@RequestParam(name = "newComments") List<WorkItemComment> newComments){
workitem.resetCommentsAndReplaceWith(newComments);
}


@GetMapping
("/isInWorkStream")
public boolean isInWorkStream(@RequestParam(name = "workStream") String workStream){
  return workitem.isInWorkStream(workStream);
}


@GetMapping
("/withNewParent")
public WorkItem withNewParent(@RequestParam(name = "newParentId") int newParentId){
  return workitem.withNewParent(newParentId);
}


}