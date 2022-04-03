package com.metservice.kanban.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class CumulativeFlowChartBuilderController {

 private CumulativeFlowChartBuilder cumulativeflowchartbuilder;

 private CumulativeFlowChartBuilder cumulativeflowchartbuilder;


@GetMapping
("/createChart")
public JFreeChart createChart(@RequestParam(name = "dataset") CategoryDataset dataset,@RequestParam(name = "project") KanbanProject project){
  return cumulativeflowchartbuilder.createChart(dataset,project);
}


}