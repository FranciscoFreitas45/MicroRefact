package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WorkItemTypeController {

 private WorkItemType workitemtype;


@GetMapping
("/getBacklogPhase")
public String getBacklogPhase(){
  return workitemtype.getBacklogPhase();
}


@GetMapping
("/getCompletedPhase")
public String getCompletedPhase(){
  return workitemtype.getCompletedPhase();
}


@GetMapping
("/equals")
public boolean equals(@RequestParam(name = "obj") Object obj){
  return workitemtype.equals(obj);
}


@GetMapping
("/containsPhase")
public boolean containsPhase(@RequestParam(name = "phase") String phase){
  return workitemtype.containsPhase(phase);
}


@PutMapping
("/setName")
public void setName(@RequestParam(name = "name") String name){
workitemtype.setName(name);
}


@PutMapping
("/setCardColour")
public void setCardColour(@RequestParam(name = "cardColour") HtmlColour cardColour){
workitemtype.setCardColour(cardColour);
}


@PutMapping
("/setBackgroundColour")
public void setBackgroundColour(@RequestParam(name = "backgroundColour") HtmlColour backgroundColour){
workitemtype.setBackgroundColour(backgroundColour);
}


}