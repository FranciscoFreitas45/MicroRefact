import java.util.ArrayList;
import java.util.List;
public class CalendarDTO {

 private  Long id;

 private  String title;

 private  String description;

 private  String color;

 private  String assignedColor;

 private  Boolean hidden;

 private  Boolean editable;

 private  List<SchedulEventDto> eventStore;


public void setEventStore(List<SchedulEventDto> eventStore){
    this.eventStore = eventStore;
}


public Boolean getHidden(){
    return hidden;
}


public Boolean getEditable(){
    return editable;
}


public void setTitle(String title){
    this.title = title;
}


public String getColor(){
    return color;
}


public String getAssignedColor(){
    return assignedColor;
}


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


public List<SchedulEventDto> getEventStore(){
    return eventStore;
}


public void setAssignedColor(String assignedColor){
    this.assignedColor = assignedColor;
}


}