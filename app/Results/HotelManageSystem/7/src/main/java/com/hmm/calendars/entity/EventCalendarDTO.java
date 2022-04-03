package com.hmm.calendars.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
public class EventCalendarDTO {

 private  Long id;

 private  String title;

 private  Date startDate;

 private  Date endDate;

 private  String calendar;

 private  String description;

 private  String empName;

 private  String empNo;

 private  String deptName;


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public void entityToDto(SchedulEvent entity,EventCalendarDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setTitle(String title){
    this.title = title;
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


public void setDescription(String description){
    this.description = description;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
}


public String getCalendar(){
    return calendar;
}


public String getDescription(){
    return description;
}


public String getDeptName(){
    return deptName;
}


public String getEmpName(){
    return empName;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
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


public void dtoToEntity(EventCalendarDTO dto,SchedulEvent entity){
    BeanUtils.copyProperties(dto, entity);
}


}