package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanPropertiesFileController {

 private KanbanPropertiesFile kanbanpropertiesfile;


@GetMapping
("/getPhases")
public String[] getPhases(@RequestParam(name = "workItemType") String workItemType){
  return kanbanpropertiesfile.getPhases(workItemType);
}


@GetMapping
("/getPhaseWIPLimit")
public String[] getPhaseWIPLimit(@RequestParam(name = "workItemType") String workItemType){
  return kanbanpropertiesfile.getPhaseWIPLimit(workItemType);
}


@GetMapping
("/getPhaseSequence")
public String[] getPhaseSequence(@RequestParam(name = "boardType") BoardIdentifier boardType){
  return kanbanpropertiesfile.getPhaseSequence(boardType);
}


@GetMapping
("/isChildWorkItemType")
public boolean isChildWorkItemType(@RequestParam(name = "name") String name,@RequestParam(name = "possibleChildName") String possibleChildName){
  return kanbanpropertiesfile.isChildWorkItemType(name,possibleChildName);
}


@GetMapping
("/getWorkItemTypes")
public String[] getWorkItemTypes(){
  return kanbanpropertiesfile.getWorkItemTypes();
}


@GetMapping
("/getWorkItemTypeCardColour")
public HtmlColour getWorkItemTypeCardColour(@RequestParam(name = "workItemType") String workItemType){
  return kanbanpropertiesfile.getWorkItemTypeCardColour(workItemType);
}


@GetMapping
("/getWorkItemTypeBackgroundColour")
public HtmlColour getWorkItemTypeBackgroundColour(@RequestParam(name = "workItemType") String workItemType){
  return kanbanpropertiesfile.getWorkItemTypeBackgroundColour(workItemType);
}


}