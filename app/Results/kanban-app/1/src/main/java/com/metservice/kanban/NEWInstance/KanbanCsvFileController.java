package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class KanbanCsvFileController {

 private KanbanCsvFile kanbancsvfile;

 private KanbanCsvFile kanbancsvfile;


@PutMapping
("/write")
public void write(@RequestParam(name = "workItems") List<WorkItem> workItems){
kanbancsvfile.write(workItems);
}


}