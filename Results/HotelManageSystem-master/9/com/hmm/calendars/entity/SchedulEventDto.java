import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import com.fasterxml.jackson.annotation.JsonFormat;
public class SchedulEventDto {

 private  Long id;

 private  String title;

 private  Boolean allDay;

 private  Date startDate;

 private  Date endDate;

 private  Long calendarId;

 private  String description;

 private  String empName;

 private  String empNo;


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public Long getCalendarId(){
    return calendarId;
}


public void entityToDto(SchedulEvent entity,SchedulEventDto dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setTitle(String title){
    this.title = title;
}


public void setCalendarId(Long calendarId){
    this.calendarId = calendarId;
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public Long getId(){
    return id;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getEndDate(){
    return endDate;
}


public void setAllDay(Boolean allDay){
    this.allDay = allDay;
}


public void setDescription(String description){
    this.description = description;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public String getDescription(){
    return description;
}


public String getEmpName(){
    return empName;
}


public Boolean getAllDay(){
    return allDay;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getTitle(){
    return title;
}


public String getEmpNo(){
    return empNo;
}


public void setId(Long id){
    this.id = id;
}


public void dtoToEntity(SchedulEventDto dto,SchedulEvent entity){
    BeanUtils.copyProperties(dto, entity);
}


}