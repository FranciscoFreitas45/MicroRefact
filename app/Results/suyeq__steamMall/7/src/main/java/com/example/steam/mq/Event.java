package com.example.steam.mq;
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
public void setEventType(EventType eventType){
    this.eventType = eventType;
}


public EventType getEventType(){
    return eventType;
}


public Map getEtrMsg(){
    return etrMsg;
}


public Event setEtrMsg(String key,String value){
    etrMsg.put(key, value);
    return this;
}


}