package com.metservice.kanban.model;
 import java.util.Arrays.asList;
import java.util.Collections.unmodifiableList;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
public class WorkItemType {

 private  HtmlColour DEFAULT_COLOUR;

 private  List<String> phases;

 private  String name;

 private  HtmlColour cardColour;

 private  HtmlColour backgroundColour;

public WorkItemType(String... phases) {
    this.phases = new ArrayList<String>(asList(phases));
}
public void setName(String name){
    this.name = name;
}


public String getName(){
    return name;
}


public List<String> getWallPhases(){
    return phases.subList(1, phases.size() - 1);
}


public void setBackgroundColour(HtmlColour backgroundColour){
    this.backgroundColour = backgroundColour;
}


public void setCardColour(HtmlColour cardColour){
    this.cardColour = cardColour;
}


public HtmlColour getBackgroundColour(){
    return backgroundColour;
}


public String getCompletedPhase(){
    return phases.get(phases.size() - 1);
}


public boolean isPhaseBefore(String phaseA,String phaseB){
    int indexA = getPhases().indexOf(phaseA);
    int indexB = getPhases().indexOf(phaseB);
    return indexA < indexB;
}


public List<String> getPhases(){
    return unmodifiableList(phases);
}


public boolean containsPhase(String phase){
    return getPhases().indexOf(phase) >= 0;
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
}


@Override
public boolean equals(Object obj){
    return super.equals(obj);
}


public boolean hasPhaseAfter(String phase){
    if (phase == null) {
        return phases.size() > 0;
    } else {
        return phases.indexOf(phase) < phases.size() - 1;
    }
}


@Override
public String toString(){
    return name;
}


public HtmlColour getCardColour(){
    return cardColour;
}


public String getPhaseAfter(String phase){
    if (!hasPhaseAfter(phase)) {
        throw new IllegalArgumentException("there is no phase after: " + phase);
    }
    int phaseIndex = phases.indexOf(phase);
    return phases.get(phaseIndex + 1);
}


public String getBacklogPhase(){
    return phases.get(0);
}


public boolean isPhaseAfter(String phaseA,String phaseB){
    return !StringUtils.equals(phaseA, phaseB) && !isPhaseBefore(phaseA, phaseB);
}


}