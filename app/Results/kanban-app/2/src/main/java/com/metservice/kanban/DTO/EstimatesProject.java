package com.metservice.kanban.DTO;
 import java.lang.Math.round;
import java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import org.joda.time.LocalDate;
import com.metservice.kanban.Interface.KanbanProject;
import com.metservice.kanban.DTO.WorkItem;
public class EstimatesProject {

 private  List<WorkItem> plannedFeatures;

 private  List<WorkItem> completedFeatures;

 private  int budget;

 private  int estimatedCostPerPoint;

 private  Map<LocalDate,Integer> costDailyMap;

 private  String projectName;

 private  KanbanProject kanbanProject;


public String getProjectName(){
    return projectName;
}


public List<WorkItem> getCompletedFeatures(){
    return completedFeatures;
}


public KanbanProject getKanbanProject(){
    return kanbanProject;
}


public int getBudget(){
    return budget;
}


public Map<LocalDate,Integer> getDayCosts(){
    return costDailyMap;
}


public List<EstimatesBudgetEntry> getBudgetEntries(){
    List<EstimatesBudgetEntry> entries = new ArrayList<EstimatesBudgetEntry>();
    for (int i = 0; i < plannedFeatures.size(); i++) {
        WorkItem previous = null;
        WorkItem next = null;
        if (i > 0) {
            previous = plannedFeatures.get(i - 1);
        }
        if (i < plannedFeatures.size() - 1) {
            next = plannedFeatures.get(i + 1);
        }
        WorkItem current = plannedFeatures.get(i);
        EstimatesBudgetEntry entry = new EstimatesBudgetEntry(current, previous, next);
        entries.add(entry);
    }
    calculateCumulativeCostAverageGuess(entries);
    calculateCumulativeCostWorstCase(entries);
    identifyBudgetOverruns(entries);
    return entries;
}


public int getCompletedPoints(){
    int sum = 0;
    for (WorkItem feature : completedFeatures) {
        sum += feature.getAverageCaseEstimate();
    }
    return sum;
}


public WorkItem getFeature(int id){
    return plannedFeatures.get(getIndexOfFeature(id));
}


public int getCostPerPointSoFar(){
    if (getCompletedPoints() == 0) {
        return 0;
    }
    return round((float) getCostSoFar() / (float) getCompletedPoints());
}


public int getCostSoFar(){
    int result = 0;
    for (LocalDate day : costDailyMap.keySet()) {
        result += costDailyMap.get(day);
    }
    return result;
}


public int getEstimatedCostPerPoint(){
    return estimatedCostPerPoint;
}


public int getIndexOfFeature(int id){
    for (int index = 0; index < plannedFeatures.size(); index++) {
        if (plannedFeatures.get(index).getId() == id) {
            return index;
        }
    }
    throw new NoSuchElementException("feature id = " + id);
}


}