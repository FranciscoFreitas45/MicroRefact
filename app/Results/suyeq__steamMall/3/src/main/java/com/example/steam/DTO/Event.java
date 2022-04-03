package com.example.steam.DTO;
 import java.util.HashMap;
import java.util.Map;
public class Event {

 public  String EVENT_KEY;

 public  String EMAIL;

 public  String SPIKE;

 public  String USER_ID;

 public  String NEW_WORD;

 private  EventType eventType;

 private  Map<String,String> etrMsg;

public Event() {
}public Event(EventType eventType) {
    this.eventType = eventType;
}
public EventType getEventType(){
    return eventType;
}


public Map getEtrMsg(){
    return etrMsg;
}


}