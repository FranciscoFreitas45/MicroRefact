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


public HtmlColour getBackgroundColour(){
    return backgroundColour;
}


public String getCompletedPhase(){
    return phases.get(phases.size() - 1);
}


public List<String> getPhases(){
    return unmodifiableList(phases);
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


public boolean containsPhase(String phase){
    return getPhases().indexOf(phase) >= 0;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/containsPhase"))

.queryParam("phase",phase)
;
boolean aux = restTemplate.getForObject(builder.toUriString(),boolean.class);
return aux;
}


public void setName(String name){
    this.name = name;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setName"))

.queryParam("name",name)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCardColour(HtmlColour cardColour){
    this.cardColour = cardColour;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setCardColour"))

.queryParam("cardColour",cardColour)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBackgroundColour(HtmlColour backgroundColour){
    this.backgroundColour = backgroundColour;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/setBackgroundColour"))

.queryParam("backgroundColour",backgroundColour)
;
restTemplate.put(builder.toUriString(),null);
}


}