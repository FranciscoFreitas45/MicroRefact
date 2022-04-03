package com.metservice.kanban.DTO;
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

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://1";

public WorkItemType(String... phases) {
    this.phases = new ArrayList<String>(asList(phases));
}
public String getName(){
    return name;
}


public List<String> getWallPhases(){
    return phases.subList(1, phases.size() - 1);
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


public List<String> getPhases(){
    return unmodifiableList(phases);
}


@Override
public int hashCode(){
    final int prime = 31;
    int result = 1;
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
}


public boolean hasPhaseAfter(String phase){
    if (phase == null) {
        return phases.size() > 0;
    } else {
        return phases.indexOf(phase) < phases.size() - 1;
    }
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


@Override
public boolean equals(Object obj){
    return super.equals(obj);
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/equals"))

.queryParam("obj",obj)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


}