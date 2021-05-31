import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
@Entity
@Table(name = "t_schedulEvent")
public class SchedulEvent {

 private  Long id;

 private  String title;

 private  Boolean allDay;

 private  Date startDate;

 private  Date endDate;

 private  String description;

 private  String eventDate;

 private  String deptName;

 private  Calendar calendar;

 private  Employee employ;


@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "emp_id")
public Employee getEmploy(){
    return employ;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public void setTitle(String title){
    this.title = title;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getEndDate(){
    return endDate;
}


public void setEmploy(Employee employ){
    this.employ = employ;
}


public void setAllDay(Boolean allDay){
    this.allDay = allDay;
}


public void setDescription(String description){
    this.description = description;
}


@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "calendarId")
public Calendar getCalendar(){
    return calendar;
}


public String getDescription(){
    return description;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public String getDeptName(){
    return deptName;
}


public Boolean getAllDay(){
    return allDay;
}


public void setCalendar(Calendar calendar){
    this.calendar = calendar;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public String getTitle(){
    return title;
}


public String getEventDate(){
    return eventDate;
}


public void setId(Long id){
    this.id = id;
}


public void setEventDate(String eventDate){
    this.eventDate = eventDate;
}


}