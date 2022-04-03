package com.hmm.calendars.entity;
 import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.data.annotation.Transient;
@Entity
@Table(name = "t_Calendar")
public class Calendar {

 private  Long id;

 private  String title;

 private  String description;

 private  String color;

 private  String assignedColor;

 private  Boolean hidden;

 private  Boolean editable;

 private  List<SchedulEvent> eventStore;


public void setEventStore(List<SchedulEvent> eventStore){
    this.eventStore = eventStore;
}


public Boolean getHidden(){
    return hidden;
}


public Boolean getEditable(){
    return editable;
}


public String getColor(){
    return color;
}


public String getAssignedColor(){
    return assignedColor;
}


public void setTitle(String title){
    this.title = title;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public void setDescription(String description){
    this.description = description;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


public void setColor(String color){
    this.color = color;
}


public void setEditable(Boolean editable){
    this.editable = editable;
}


public void setHidden(Boolean hidden){
    this.hidden = hidden;
}


public void setId(Long id){
    this.id = id;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "calendar")
public List<SchedulEvent> getEventStore(){
    return eventStore;
}


public void setAssignedColor(String assignedColor){
    this.assignedColor = assignedColor;
}


}