package com.hmm.DTO;
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
public class Calendar {

 private  Long id;

 private  String title;

 private  String description;

 private  String color;

 private  String assignedColor;

 private  Boolean hidden;

 private  Boolean editable;

 private  List<SchedulEvent> eventStore;


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


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


public String getDescription(){
    return description;
}


public String getTitle(){
    return title;
}


@OneToMany(cascade = CascadeType.MERGE, mappedBy = "calendar")
public List<SchedulEvent> getEventStore(){
    return eventStore;
}


}