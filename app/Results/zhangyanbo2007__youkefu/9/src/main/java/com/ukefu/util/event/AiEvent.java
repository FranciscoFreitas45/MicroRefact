package com.ukefu.util.event;
 public class AiEvent {

 private  long id;

 private  UserEvent event;


public UserEvent getEvent(){
    return event;
}


public void setId(long id){
    this.id = id;
}


public long getId(){
    return id;
}


public void setEvent(UserEvent event){
    this.event = event;
}


}