package com.metservice.kanban.charts.burnup;
 import java.util.ArrayList;
import java.util.List;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.internal.Pair;
import com.metservice.kanban.model.EstimatesProject;
import com.metservice.kanban.model.WorkItem;
public class EstimatesBurnDownDataModel {

 private  Logger LOGGER;

 private  List<WorkItem> workItems;

 private  EstimatesProject estimatesProject;

 private  int allFeaturePoints;

 private  List<Pair<Integer,LocalDate>> budgetEntries;

 private  int projectedBudgedConsumed;

public EstimatesBurnDownDataModel(List<WorkItem> workItems, EstimatesProject estimatesProject) {
    this.workItems = removeExcludedWorkItems(workItems);
    this.estimatesProject = estimatesProject;
    this.allFeaturePoints = computeAllFeaturePoints();
    this.budgetEntries = computeBudgetEntries();
    this.projectedBudgedConsumed = computeProjectedBudgedConsumed();
}
public int computeAllFeaturePoints(){
    int points = 0;
    for (WorkItem wi : workItems) {
        points += wi.getAverageCaseEstimate();
    }
    return points;
}


public int getRemainingFeaturePointForBudget(Pair<Integer,LocalDate> budgetEntry){
    int points = getAllFeaturePoints();
    for (WorkItem wi : workItems) {
        if (wi.isCompleted() && !wi.getDate(wi.getCurrentPhase()).isAfter(budgetEntry.second)) {
            points -= wi.getAverageCaseEstimate();
        }
    }
    return points;
}


public int getAllFeaturePoints(){
    return allFeaturePoints;
}


public int getProjectedBudgetConsumed(){
    return projectedBudgedConsumed;
}


public int getBudget(){
    return estimatesProject.getBudget();
}


public List<Pair<Integer,LocalDate>> getBudgetEntries(){
    return budgetEntries;
}


public int computeProjectedBudgedConsumed(){
    Pair<Integer, LocalDate> lastBudgedEntry = getLastBudgedEntry();
    if (lastBudgedEntry == null) {
        LOGGER.info("Trying to getProjectedBudget with no budget recorded, should return infinity");
        // just some large value to indicate that sth is wrong
        return getBudget() * 5;
    }
    int remainingFeaturePointForBudget = getRemainingFeaturePointForBudget(lastBudgedEntry);
    if (allFeaturePoints - remainingFeaturePointForBudget != 0) {
        return lastBudgedEntry.first * allFeaturePoints / (allFeaturePoints - remainingFeaturePointForBudget);
    } else {
        LOGGER.info("Trying to getProjectedBudget with no finished work items, should return infinity");
        // just some large value to indicate that sth is wrong
        return getBudget() * 5;
    }
}


public List<WorkItem> removeExcludedWorkItems(List<WorkItem> workItemList){
    List<WorkItem> list = new ArrayList<WorkItem>();
    for (WorkItem item : workItemList) {
        if (!item.isExcluded()) {
            list.add(item);
        }
    }
    return list;
}


public List<Pair<Integer,LocalDate>> computeBudgetEntries(){
    List<Pair<Integer, LocalDate>> result = new ArrayList<Pair<Integer, LocalDate>>();
    int currentBudget = 0;
    for (LocalDate day : estimatesProject.getDayCosts().keySet()) {
        Integer integer = estimatesProject.getDayCosts().get(day);
        currentBudget += integer;
        result.add(new Pair<Integer, LocalDate>(currentBudget, day));
    }
    return result;
}


public Pair<Integer,LocalDate> getLastBudgedEntry(){
    if (budgetEntries == null || budgetEntries.size() == 0) {
        return null;
    }
    return budgetEntries.get(budgetEntries.size() - 1);
}


}