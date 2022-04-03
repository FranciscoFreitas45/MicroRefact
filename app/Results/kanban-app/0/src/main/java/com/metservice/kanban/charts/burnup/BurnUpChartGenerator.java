package com.metservice.kanban.charts.burnup;
 import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import org.joda.time.LocalDate;
import com.metservice.kanban.model.EstimatesProject;
import com.metservice.kanban.model.KanbanProject;
import com.metservice.kanban.model.WorkItem;
import com.metservice.kanban.model.WorkItemType;
public interface BurnUpChartGenerator {


public void generateBurnUpChart(KanbanProject project,WorkItemType type,List<WorkItem> workItems,LocalDate startDate,LocalDate currentDate,OutputStream outputStream)
;

public void generateEstimatesBurnUpChart(EstimatesProject project,List<WorkItem> workItems,OutputStream outputStream)
;

}