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


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getId(){
    return id;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getEndDate(){
    return endDate;
}


@ManyToOne(cascade = CascadeType.MERGE)
@JoinColumn(name = "calendarId")
public Calendar getCalendar(){
    return calendar;
}


public String getDescription(){
    return description;
}


public String getDeptName(){
    return deptName;
}


public Boolean getAllDay(){
    return allDay;
}


public String getTitle(){
    return title;
}


public String getEventDate(){
    return eventDate;
}


}