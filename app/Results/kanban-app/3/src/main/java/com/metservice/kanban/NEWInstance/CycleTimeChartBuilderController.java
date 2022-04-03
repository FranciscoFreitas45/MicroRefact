package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CycleTimeChartBuilderController {

 private CycleTimeChartBuilder cycletimechartbuilder;

 private CycleTimeChartBuilder cycletimechartbuilder;


@GetMapping
("/createDataset")
public CategoryDataset createDataset(@RequestParam(name = "listOfItems") Collection<WorkItem> listOfItems){
  return cycletimechartbuilder.createDataset(listOfItems);
}


@GetMapping
("/createChart")
public JFreeChart createChart(@RequestParam(name = "categorydataset") CategoryDataset categorydataset){
  return cycletimechartbuilder.createChart(categorydataset);
}


}