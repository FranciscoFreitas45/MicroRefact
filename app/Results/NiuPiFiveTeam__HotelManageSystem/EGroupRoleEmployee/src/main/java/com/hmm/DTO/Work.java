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

 private Integer emp_idSDYK;


public Integer getNormal(){
    return normal;
}


@Transient
public Employee getEmploy(){
  this.employ = employeerequest.getEmploy(this.emp_idSDYK);
return this.employ;
}}



public Integer getLeaveEarly(){
    return leaveEarly;
}


public Float getOvertime(){
    return overtime;
}


public Float getWorktime(){
    return worktime;
}


public Integer getLackCard(){
    return lackCard;
}


public String getCalendar(){
    return calendar;
}


public String getWorkDate(){
    return workDate;
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


@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
public Date getOntudytime(){
    return ontudytime;
}


public Integer getLate(){
    return late;
}


}