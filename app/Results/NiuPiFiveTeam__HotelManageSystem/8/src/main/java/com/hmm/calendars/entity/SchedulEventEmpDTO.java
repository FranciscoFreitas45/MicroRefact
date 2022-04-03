package com.hmm.calendars.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import com.fasterxml.jackson.annotation.JsonFormat;
public class SchedulEventEmpDTO {

 private  Long id;

 private  Date startDate;

 private  String calendar;

 private  Date endDate;

 private  String empName;

 private  String empNo;

 private  String deptName;

 private  String eventDate;


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
public Date getStartDate(){
    return startDate;
}


public void setStartDate(Date startDate){
    this.startDate = startDate;
}


public void entityToDto(SchedulEvent entity,SchedulEventEmpDTO dto){
    BeanUtils.copyProperties(entity, dto);
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


public String getCalendar(){
    return calendar;
}


public void setEndDate(Date endDate){
    this.endDate = endDate;
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


public String getEmpNo(){
    return empNo;
}


public String getEventDate(){
    return eventDate;
}


public void setId(Long id){
    this.id = id;
}


public void dtoToEntity(SchedulEventEmpDTO dto,SchedulEvent entity){
    BeanUtils.copyProperties(dto, entity);
}


public void setEventDate(String eventDate){
    this.eventDate = eventDate;
}


}