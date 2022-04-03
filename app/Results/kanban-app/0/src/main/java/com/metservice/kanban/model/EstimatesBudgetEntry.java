package com.metservice.kanban.model;
 import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
import com.metservice.kanban.Interface.WorkItem;
public class EstimatesBudgetEntry {

 private  WorkItem feature;

 private  int averageCaseCumulativeCost;

 private  int worstCaseCumulativeCost;

 private  boolean overBudgetInWorstCase;

 private  boolean overBudgetInAverageCase;

 private  WorkItem nextFeature;

 private  WorkItem prevFeature;

public EstimatesBudgetEntry(WorkItem feature, WorkItem prevFeature, WorkItem nextFeature) {
    this.feature = feature;
    this.prevFeature = prevFeature;
    this.nextFeature = nextFeature;
}
public int getWorstCaseCumulativeCost(){
    return worstCaseCumulativeCost;
}


public WorkItem getNextFeature(){
    return nextFeature;
}


public boolean isOverBudgetInAverageCase(){
    return overBudgetInAverageCase;
}


public WorkItem getPrevFeature(){
    return prevFeature;
}


public void setOverBudgetInAverageCase(boolean overBudgetInAverageCase){
    this.overBudgetInAverageCase = overBudgetInAverageCase;
}


public void setOverBudgetInWorstCase(boolean overBudgetInWorstCase){
    this.overBudgetInWorstCase = overBudgetInWorstCase;
}


public void setWorstCaseCumulativeCost(int worstCaseCumulativeCost){
    this.worstCaseCumulativeCost = worstCaseCumulativeCost;
}


public boolean isOverBudgetInWorstCase(){
    return overBudgetInWorstCase;
}


public void setAverageCaseCumulativeCost(int averageCaseCumulativeCost){
    this.averageCaseCumulativeCost = averageCaseCumulativeCost;
}


public int getAverageCaseCumulativeCost(){
    return averageCaseCumulativeCost;
}


public WorkItem getFeature(){
    return feature;
}


}