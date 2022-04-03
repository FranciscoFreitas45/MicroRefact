package com.hmm.Work.entity;
 import java.util.Date;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.employee.entity.Employee;
import com.hmm.employee.entity.EmployeeDTO;
public class WorkEmpDTO {

 private  Long workid;

 private  Float worktime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date ontudytime;

@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
 private  Date offdutytime;

 private  Float overtime;

 private  Integer normal;

 private  Integer late;

 private  Integer lackCard;

 private  Integer leaveEarly;

 private  String empNo;

 private  String empName;

 private  String deptName;

 private  String calendar;


public void setWorkid(Long workid){
    this.workid = workid;
}


public Integer getNormal(){
    return normal;
}


public void entityToDto(Work entity,WorkEmpDTO dto){
    BeanUtils.copyProperties(entity, dto);
}


public void setEmpNo(String empNo){
    this.empNo = empNo;
}


public void setOvertime(Float overtime){
    this.overtime = overtime;
}


public Integer getLackCard(){
    return lackCard;
}


public void setLeaveEarly(Integer leaveEarly){
    this.leaveEarly = leaveEarly;
}


public String getCalendar(){
    return calendar;
}


public void setLackCard(Integer lackCard){
    this.lackCard = lackCard;
}


public String getEmpName(){
    return empName;
}


public void setEmpName(String empName){
    this.empName = empName;
}


public String getEmpNo(){
    return empNo;
}


public void setWorktime(Float worktime){
    this.worktime = worktime;
}


public Long getWorkid(){
    return workid;
}


public Date getOffdutytime(){
    return offdutytime;
}


public Integer getLate(){
    return late;
}


public Integer getLeaveEarly(){
    return leaveEarly;
}


public void setLate(Integer late){
    this.late = late;
}


public Float getOvertime(){
    return overtime;
}


public Float getWorktime(){
    return worktime;
}


public String getDeptName(){
    return deptName;
}


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public void setDeptName(String deptName){
    this.deptName = deptName;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public void setNormal(Integer normal){
    this.normal = normal;
}


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


public Date getOntudytime(){
    return ontudytime;
}


public void dtoToEntity(WorkEmpDTO dto,Work entity){
    BeanUtils.copyProperties(dto, entity);
}


}