package com.hmm.DTO;
 import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
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
import com.fasterxml.jackson.annotation.JsonFormat;
import com.hmm.activiti.domain.ProcessStatus;
import com.hmm.employee.entity.Employee;
import com.hmm.Request.EmployeeRequest;
import com.hmm.Request.Impl.EmployeeRequestImpl;
import com.hmm.DTO.Employee;
public class Work {

 private  Long workid;

 private  Integer normal;

 private  Integer late;

 private  Integer lackCard;

 private  Integer leaveEarly;

 private  Float worktime;

 private  Date ontudytime;

 private  Date offdutytime;

 private  Float overtime;

 private  String calendar;

 private  String workDate;

 private  Employee employ;

 private Integer emp_idLNBF;

 private EmployeeRequest employeerequest = new EmployeeRequestImpl();;


public void setWorkid(Long workid){
    this.workid = workid == null ? null : workid;
}


public Integer getNormal(){
    return normal;
}


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idLNBF);
return this.employ;
}}



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


public void setOvertime(Float overtime){
    this.overtime = overtime;
}


public void setEmploy(Employee employ){
this.emp_idLNBF = employ.getEmploy() ;
employeerequest.setEmploy(employ,this.emp_idLNBF);
 this.employ = employ;
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


public void setOntudytime(Date ontudytime){
    this.ontudytime = ontudytime;
}


public void setLackCard(Integer lackCard){
    this.lackCard = lackCard;
}


public void setCalendar(String calendar){
    this.calendar = calendar;
}


public String getWorkDate(){
    return workDate;
}


public void setWorktime(Float worktime){
    this.worktime = worktime;
}


public void setNormal(Integer normal){
    this.normal = normal;
}


@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
public Long getWorkid(){
    return workid;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getOffdutytime(){
    return offdutytime;
}


public void setOffdutytime(Date offdutytime){
    this.offdutytime = offdutytime;
}


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getOntudytime(){
    return ontudytime;
}


public Integer getLate(){
    return late;
}


public void setWorkDate(String workDate){
    this.workDate = workDate;
}


}